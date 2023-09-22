package com.example.borutoapp.di

import android.content.Context
import androidx.room.Room
import com.example.borutoapp.data.local.BorutoDATABASE
import com.example.borutoapp.util.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun providaDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        BorutoDATABASE::class.java,
        BORUTO_DATABASE
        ).build()
}