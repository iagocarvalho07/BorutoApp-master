package com.example.borutoapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.example.borutoapp.data.repository.Repository
import com.example.borutoapp.domain.module.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCases(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Hero>>{
        return repository.getallHeroes()
    }
}