package com.baish.skyscanner.data.di

import com.baish.skyscanner.data.interactor.NasaInteractor
import com.baish.skyscanner.data.interactor.NasaInteractorImpl
import com.baish.skyscanner.data.remote.NasaService
import com.baish.skyscanner.data.remote.RetrofitBuilder
import com.baish.skyscanner.ui.main.MainViewModel
import com.baish.skyscanner.ui.main.apod.ApodViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { MainViewModel(get()) }
    viewModel { ApodViewModel(get()) }
}

val repositoryModule: Module = module {

}

val apiModule: Module = module {
    single<NasaService> { RetrofitBuilder.buildRetrofit() }
    single<NasaInteractor> { NasaInteractorImpl(get()) }
}

val appModules =
    listOf(viewModelModule, apiModule, repositoryModule)