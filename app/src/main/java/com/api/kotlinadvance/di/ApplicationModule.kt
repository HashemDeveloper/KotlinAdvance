package com.api.kotlinadvance.di

import android.content.Context
import com.api.kotlinadvance.KotlinAdvance
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun provideContext(kotlinAdvance: KotlinAdvance) : Context {
        return kotlinAdvance
    }
}