
package com.altamirano.dagger.di.module

import com.altamirano.dagger.api.IApiService
import com.altamirano.dagger.util.Constants

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    private val baseURL: String
    private val debug:Boolean = com.altamirano.fabricio.marvelbook.BuildConfig.DEBUG
    private val timeout:Int = Constants.DEFAULT_TIMEOUT

    init {
        // TODO Target debug or Release with variants
        baseURL = Constants.BASE_URL
    }

    @Provides

     fun providerGson()=GsonBuilder().create()


    @Provides
    fun provideOkHttpClient(): OkHttpClient.Builder{
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
        clientBuilder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        return clientBuilder
    }

    @Provides
    fun provideAPIService(retrofit: Retrofit) : IApiService {

        return retrofit.create(IApiService::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClientBuilder: OkHttpClient.Builder, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClientBuilder.build())
            .build()
    }
}