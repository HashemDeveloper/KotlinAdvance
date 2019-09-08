package com.api.kotlinadvance.di.networking

import com.api.kotlinadvance.data.CountryRepoModule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [CountryServiceModule::class, CountryRepoModule::class])
object ServiceModule {
    @Singleton
    @Provides
    @JvmStatic
    internal fun provideGson() : Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();
    }
    @Singleton
    @Provides
    @JvmStatic
    internal fun provideRetrofit(gson: Gson, @Named("base_url") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .build();
    }
}