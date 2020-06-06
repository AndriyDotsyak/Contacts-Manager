package com.contact.manager

import android.app.Application
import com.contact.manager.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = androidInjector

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this@App)
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this@App)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder().build()
        )
    }
}