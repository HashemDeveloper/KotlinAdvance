package com.api.kotlinadvance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.api.kotlinadvance.R
import com.api.kotlinadvance.model.Country
import com.api.kotlinadvance.utils.getProgressDrawable
import com.api.kotlinadvance.utils.loadImage
import kotlinx.android.synthetic.main.country_list_item_layout.view.*

class CountryListAdapter(var countrys: ArrayList<Country>):
    RecyclerView.Adapter<CountryListAdapter.CountyListViewHolder>() {
    val countryList = ArrayList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountyListViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.country_list_item_layout, parent, false)
        return CountyListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.countryList.size
    }

    override fun onBindViewHolder(holder: CountyListViewHolder, position: Int) {
        holder.bindView(this.countryList[position])
    }

    fun updateCountries(countryList: List<Country>) {
        this.countryList.clear()
        this.countryList.addAll(countryList)
        notifyDataSetChanged()
    }
    // View Holder
    class CountyListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val countryNameView = view.country_list_item_name_view_id
        private val countryFlagView = view.country_list_item_image_view_id
        private val countryCapitalNameView = view.country_list_item_capital_view_id
        private val progressDrawable = getProgressDrawable(view.context)

       internal fun bindView(country: Country) {
           this.countryNameView.text = country.countryName
           this.countryCapitalNameView.text = country.capital
           this.countryFlagView.loadImage(country.flag, this.progressDrawable)
       }
    }
}