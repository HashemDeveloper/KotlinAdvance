package com.api.kotlinadvance.di

import android.app.Activity
import com.api.kotlinadvance.view.CountryListActivity
import com.api.kotlinadvance.view.CountryListComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [CountryListComponent::class])
abstract class ActivityModule{
    @Binds
    @IntoMap
    @ActivityKey(CountryListActivity::class)
    internal abstract fun bindAndroidInjectorWithWelcome(builder: CountryListComponent.Builder): AndroidInjector.Factory<out Activity>
}