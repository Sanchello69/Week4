package com.vas.week4

import android.app.Application
import com.vas.week4.di.AppComponent
import com.vas.week4.di.DaggerAppComponent
import com.vas.week4.feature_list_chat_screen.di.ListChatDepsStore

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()

        ListChatDepsStore.deps = appComponent

    }
}