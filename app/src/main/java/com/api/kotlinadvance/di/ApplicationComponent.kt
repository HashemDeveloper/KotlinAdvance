package com.api.kotlinadvance.di

import com.api.kotlinadvance.KotlinAdvance
import com.api.kotlinadvance.di.networking.ServiceModule
import com.api.kotlinadvance.di.viewmodelfactory.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, ActivityModule::class, ViewModelModule::class, ServiceModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun buildApplication(kotlinAdvance: KotlinAdvance) : Builder

        fun build() : ApplicationComponent
    }
    fun inject(kotlinAdvance: KotlinAdvance)
}