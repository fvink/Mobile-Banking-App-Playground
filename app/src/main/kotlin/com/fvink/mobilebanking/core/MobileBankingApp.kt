package com.fvink.mobilebanking.core

import android.app.Application
import com.fvink.mobilebanking.BuildConfig
import com.fvink.mobilebanking.util.LinkDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MobileBankingApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(LinkDebugTree())
        }
    }
}