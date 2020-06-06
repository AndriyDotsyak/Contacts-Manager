package com.contact.manager.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.contact.manager.screen.base.ViewModelFactory
import com.contact.manager.screen.detailedContact.DetailedContactViewModel
import com.contact.manager.screen.home.HomeViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailedContactViewModel::class)
    abstract fun bindDetailedContactViewModel(viewModel: DetailedContactViewModel): ViewModel
}