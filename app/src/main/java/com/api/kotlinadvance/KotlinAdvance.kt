package com.api.kotlinadvance

import android.app.Activity
import android.app.Application
import com.api.kotlinadvance.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class KotlinAdvance: Application(), HasActivityInjector{
    @Inject
    lateinit var  dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().buildApplication(this).build().inject(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> {
        return this.dispatchingAndroidInjector
    }
}