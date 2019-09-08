package com.api.kotlinadvance.view

import com.api.kotlinadvance.di.scopes.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent
interface CountryListComponent: AndroidInjector<CountryListActivity> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<CountryListActivity>() {

    }
}