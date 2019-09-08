package com.api.kotlinadvance.di.networking

import dagger.Module
import dagger.Provides
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
object CountryServiceModule{
    @Provides
    @Singleton
    @JvmStatic
    internal fun provideOkHttpClient(): Call.Factory {
        return OkHttpClient.Builder()
            .build();
    }

    @Provides
    @JvmStatic
    @Named("base_url")
    internal fun provideBaseUrl(): String {
        return "https://raw.githubusercontent.com";
    }

}