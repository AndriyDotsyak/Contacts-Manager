package com.contact.manager.screen.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.contact.manager.di.Injectable
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
}