package com.biblublab.skelettonmvi.di

import com.biblublab.data.RestApi
import com.biblublab.skelettonmvi.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule  {

    @Provides
    @Singleton
     fun providesLoggingInterceptor(): HttpLoggingInterceptor? =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else null

    @Provides
    @Singleton
    internal fun providesOkHttpClientBuilder(loggingInterceptor: HttpLoggingInterceptor?): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                loggingInterceptor?.also {
                    addInterceptor(it)
                }
            }.build()

    @Provides
    @Singleton
    internal fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    @Provides
    internal fun provideRetrofitApi(retrofit: Retrofit) : RestApi =
        retrofit.create(RestApi::class.java)


}