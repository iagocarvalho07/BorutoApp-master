package com.example.borutoapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.borutoapp.data.local.BorutoDATABASE
import com.example.borutoapp.data.remote.BorutoApi
import com.example.borutoapp.domain.module.Hero
import com.example.borutoapp.domain.module.HeroRepoteKEY
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMidiator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val borutoDATABASE: BorutoDATABASE
) : RemoteMediator<Int, Hero>() {

    private val heroDao = borutoDATABASE.HeroDao()
    private val remoteKeyDao = borutoDATABASE.HeroRemoteKeyDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = remoteKeyDao.getRemoteKey(id = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {
            val page = when (loadType){
                LoadType.REFRESH ->{
                    val remoteKeys = getRemoteKeyCurrentPosition(state)
                    remoteKeys?.nextpage?.minus(1) ?: 1
                }
                LoadType.PREPEND ->{
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prepage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextpage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = borutoApi.getAllHeroes(page = page )
            if (response.heroes.isNotEmpty()) {
                borutoDATABASE.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeros()
                        remoteKeyDao.deleteAllRemoteKey()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map { hero ->
                        HeroRepoteKEY(
                            id = hero.id,
                            prepage = prevPage,
                            nextpage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    remoteKeyDao.AddAllRemoteKey(heroRepoteKEY = keys)
                    heroDao.adHeros(Heroes = response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyCurrentPosition(
        state: PagingState<Int, Hero>
    ): HeroRepoteKEY? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {id->
                remoteKeyDao.getRemoteKey(id = id)
            }
        }

    }
    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Hero>
    ): HeroRepoteKEY? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
                remoteKeyDao.getRemoteKey(id = hero.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Hero>
    ): HeroRepoteKEY? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                remoteKeyDao.getRemoteKey(id = hero.id)
            }
    }

        private fun parseMillis(millis: Long): String {
        val date = Date(millis)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
        return format.format(date)
    }
}