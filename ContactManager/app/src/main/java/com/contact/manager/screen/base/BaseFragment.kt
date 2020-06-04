package com.contact.manager.screen.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.contact.manager.di.Injectable
import javax.inject.Inject

abstract class BaseFragment : Fragment(), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
}