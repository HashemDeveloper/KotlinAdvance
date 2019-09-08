package com.api.kotlinadvance

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.api.kotlinadvance.data.ICountriesApi
import com.api.kotlinadvance.model.Country
import com.api.kotlinadvance.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ViewModelListTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var countryListService: ICountriesApi

    @InjectMocks
    var listViewModel = ListViewModel()
    private var testSingle: Single<List<Country>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
               return ExecutorScheduler.ExecutorWorker(Executor { it.run()}, false)
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler {
            schedular -> immediate
        }
        RxJavaPlugins.setInitComputationSchedulerHandler {
            schedular -> immediate
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler {
            schedular -> immediate
        }
        RxJavaPlugins.setInitSingleSchedulerHandler {
            schedular -> immediate
        }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            schedular -> immediate
        }
    }

    @Test
    fun getCountriesSuccess() {
        val country = Country("countryName", "capital", "url");
        val countryList = arrayListOf<Country>(country)
        this.testSingle = Single.just(countryList)
        `when`(this.countryListService.getCountries()).thenReturn(this.testSingle)
        this.listViewModel.refresh()
        Assert.assertEquals(1, this.listViewModel.countryLiveData.value?.size)
        Assert.assertEquals(false, this.listViewModel.isLoading.value)
        Assert.assertEquals(false, this.listViewModel.isError.value)
    }
    @Test
    fun getCountryFailed() {
        this.testSingle = Single.error(Throwable())
        `when`(this.countryListService.getCountries()).thenReturn(this.testSingle)
        this.listViewModel.refresh()
        Assert.assertEquals(false, this.listViewModel.isLoading.value)
        Assert.assertEquals(true, this.listViewModel.isError.value)
    }
}