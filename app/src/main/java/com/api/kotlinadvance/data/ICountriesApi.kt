package com.api.kotlinadvance.data

import com.api.kotlinadvance.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface ICountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<List<Country>>
}