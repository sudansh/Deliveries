package com.sudansh.deliveries.di

import com.sudansh.deliveries.ui.MainViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext


val appModule = applicationContext {

    viewModel { MainViewModel(get()) }
}