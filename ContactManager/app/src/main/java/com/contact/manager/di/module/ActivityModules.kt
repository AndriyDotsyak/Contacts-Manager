package com.contact.manager.di.module

import com.contact.manager.screen.detailedContact.DetailedContactActivity
import com.contact.manager.screen.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}

@Module
abstract class DetailedContactActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeDetailedContactActivity(): DetailedContactActivity
}