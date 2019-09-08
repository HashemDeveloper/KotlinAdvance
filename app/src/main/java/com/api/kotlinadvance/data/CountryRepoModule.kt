package com.api.kotlinadvance.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object CountryRepoModule {
    @Provides
    @Singleton
    @JvmStatic
    internal fun provideCountryListRepo(retrofit: Retrofit): ICountriesApi {
        return retrofit.create(ICountriesApi::class.java)
    }
}