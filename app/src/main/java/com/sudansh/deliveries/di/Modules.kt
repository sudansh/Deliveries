package com.sudansh.deliveries.di

import com.sudansh.deliveries.ui.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val appModule = module {
    viewModel { MainViewModel(get()) }
}