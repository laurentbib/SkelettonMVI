package com.biblublab.skelettonmvi.features.home

import com.biblublab.data.repository.DataRepositoryImpl
import com.biblublab.domain.repository.DataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class HomeModule {

    @Binds
    abstract fun bindDataRepository(dataRepositoryImpl: DataRepositoryImpl) : DataRepository

}