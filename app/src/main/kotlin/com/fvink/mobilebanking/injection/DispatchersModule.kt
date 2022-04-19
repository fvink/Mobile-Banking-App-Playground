package com.fvink.mobilebanking.injection

import com.fvink.mobilebanking.util.DefaultDispatcherProvider
import com.fvink.mobilebanking.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    fun bindDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
}