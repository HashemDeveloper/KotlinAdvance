package com.api.kotlinadvance

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.api.kotlinadvance.viewmodel.ListViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4ClassRunner::class)
class ListViewModelListTest {

    @Inject
    lateinit var listViewModel: ListViewModel

    @Before
    fun setup() {

    }

    @Test
    fun refresh() {
        val countryData = this.listViewModel.getCountryLiveData().value
    }
}