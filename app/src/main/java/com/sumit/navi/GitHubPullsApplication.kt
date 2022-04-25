package com.sumit.navi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitHubPullsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}