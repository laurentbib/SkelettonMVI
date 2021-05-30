package com.biblublab.skelettonmvi.di

import android.content.Context
import androidx.room.Room
import com.biblublab.data.repository.AppDataBase
import com.biblublab.data.repository.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "Skeleton_mvi_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(appDataBase: AppDataBase) : NewsDao = appDataBase.newsDao()

}