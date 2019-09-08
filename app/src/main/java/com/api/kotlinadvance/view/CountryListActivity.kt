package com.api.kotlinadvance.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.api.kotlinadvance.R
import com.api.kotlinadvance.adapters.CountryListAdapter
import com.api.kotlinadvance.di.viewmodelfactory.ViewModelFactory
import com.api.kotlinadvance.viewmodel.ListViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.country_list_layout.*
import javax.inject.Inject

class CountryListActivity : AppCompatActivity() {
    @Inject
    lateinit var modelFactory: ViewModelFactory
    lateinit var listViewModel: ListViewModel
    private val countryListAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.country_list_layout)
        this.listViewModel = ViewModelProviders.of(this, this.modelFactory).get(ListViewModel::class.java)
        this.listViewModel.refresh()

        country_list_recycler_view_id.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryListAdapter
        }
        swipe_refresh_layout_id.setOnRefreshListener {
            swipe_refresh_layout_id.isRefreshing = false
            this.listViewModel.refresh()
        }
        observeViewModel()
    }
    private fun observeViewModel() {
        this.listViewModel.countryLiveData.observe(this, Observer {countries ->
            countries?.let {
                country_list_recycler_view_id.visibility = View.VISIBLE
                this.countryListAdapter.updateCountries(it) }
        })
        this.listViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let { country_list_loading_bar_id.visibility = if(it) View.VISIBLE else View.GONE
                if (it) {
                    country_list_error_text_view_id.visibility = View.GONE
                    country_list_recycler_view_id.visibility = View.GONE
                }
            }
        })
        this.listViewModel.isError.observe(this, Observer { isError ->
            isError?.let { country_list_error_text_view_id.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}
