package com.example.borutoapp.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.borutoapp.data.local.BorutoDATABASE
import com.example.borutoapp.data.paging_source.HeroRemoteMidiator
import com.example.borutoapp.data.remote.BorutoApi
import com.example.borutoapp.domain.module.Hero
import com.example.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow


@ExperimentalPagingApi
class RemoteDataSorceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDATABASE: BorutoDATABASE
) : RemoteDataSource {

    private val heroDao = borutoDATABASE.HeroDao()

    override fun getAllData(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMidiator(
                borutoApi = borutoApi,
                borutoDATABASE = borutoDATABASE
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun serachHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }
}