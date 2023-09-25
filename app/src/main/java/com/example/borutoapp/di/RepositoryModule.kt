package com.example.borutoapp.di

import android.content.Context
import com.example.borutoapp.data.repository.DataStoreOperations
import com.example.borutoapp.data.repository.DataStoreOperationsImpl
import com.example.borutoapp.data.repository.Repository
import com.example.borutoapp.domain.use_cases.UsesCases
import com.example.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCases
import com.example.borutoapp.domain.use_cases.readOnbordingState.ReadOnOnbrodingState
import com.example.borutoapp.domain.use_cases.save_onBordingState.SaveOnbordingState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context)
    }

    @Provides
    @Singleton
    fun provideUserCases(repository: Repository): UsesCases {
        return UsesCases(
            saveOnbordingState = SaveOnbordingState(repository),
            readOnOnbrodingState = ReadOnOnbrodingState(repository),
            getAllHeroesUseCases = GetAllHeroesUseCases(repository)
        )
    }
}