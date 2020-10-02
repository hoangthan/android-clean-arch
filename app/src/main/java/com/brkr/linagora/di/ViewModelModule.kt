package com.brkr.linagora.di

import com.brkr.linagora.presentation.ui.searching.SearchingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ViewModelModule = module {
    viewModel { SearchingViewModel() }
}
