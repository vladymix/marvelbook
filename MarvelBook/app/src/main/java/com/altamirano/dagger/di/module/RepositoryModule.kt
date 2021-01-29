package com.altamirano.dagger.di.module


import com.altamirano.dagger.api.IApiService
import com.altamirano.dagger.api.MarvelRepositoryAPI
import com.altamirano.dagger.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(api: IApiService): CharactersRepository{
        return MarvelRepositoryAPI(api)
    }
}