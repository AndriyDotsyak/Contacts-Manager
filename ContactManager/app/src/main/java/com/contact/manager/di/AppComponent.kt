package com.contact.manager.di

import com.contact.manager.di.module.HomeActivityModule
import com.contact.manager.di.module.ViewModelModule
import com.contact.manager.App
import com.contact.manager.di.module.DetailedContactActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        HomeActivityModule::class,
        DetailedContactActivityModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}