package com.api.kotlinadvance.data

import com.api.kotlinadvance.model.Country
import io.reactivex.Single
import javax.inject.Inject

class CountriesRepo @Inject constructor(){

    @Inject
    lateinit var countryApi: ICountriesApi

    fun getCountryList(): Single<List<Country>> {
        return this.countryApi.getCountries()
    }
}