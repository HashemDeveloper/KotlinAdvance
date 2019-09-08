package com.api.kotlinadvance.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.api.kotlinadvance.data.ICountriesApi
import com.api.kotlinadvance.model.Country
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel @Inject constructor() : ViewModel(){
    @Inject
    lateinit var countryListApi: ICountriesApi
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    @Inject
    lateinit var context: Context
    val countryLiveData = MutableLiveData<List<Country>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
//        val mockData = listOf(
//            Country("Bangladesh"),
//            Country("Korea"),
//            Country("Ukraine"),
//            Country("China"),
//            Country("Japan")
//        )
        this.isLoading.value = true
        this.compositeDisposable.add(this.countryListApi.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                override fun onSuccess(countryList: List<Country>) {
                    countryLiveData.value = countryList
                    isLoading.value = false
                    isError.value = false
                }

                override fun onError(e: Throwable) {
                    isError.value = true
                    isLoading.value = false
                    if (e.localizedMessage != null) {
                        Log.i("Error: ", e.localizedMessage!!)
                    }
                }
            }))
    }

    override fun onCleared() {
        super.onCleared()
        this.compositeDisposable.clear()
    }

    fun getCountryLiveData(): LiveData<List<Country>> {
        return this.countryLiveData
    }
}