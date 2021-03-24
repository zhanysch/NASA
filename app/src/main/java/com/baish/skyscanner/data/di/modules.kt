package com.baish.skyscanner.data.di

import com.baish.skyscanner.data.db.AppDataBase
import com.baish.skyscanner.data.interactor.NasaInteractor
import com.baish.skyscanner.data.interactor.NasaInteractorImpl
import com.baish.skyscanner.data.remote.NasaService
import com.baish.skyscanner.data.remote.RetrofitBuilder
import com.baish.skyscanner.data.repository.NasaRepository
import com.baish.skyscanner.data.repository.NasaRepositoryImpl
import com.baish.skyscanner.ui.main.mainside.MainViewModel
import com.baish.skyscanner.ui.main.apod.ApodViewModel
import com.baish.skyscanner.ui.main.mars.MarsViewModel
import com.baish.skyscanner.ui.main.techproject.TechProjectViewModel
import com.baish.skyscanner.ui.main.videoandimage.ImageViewModel
import com.baish.skyscanner.ui.menu.features.FeaturesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { ApodViewModel(get(),get(),get()) }
    viewModel { MainViewModel(get(),get()) }
    viewModel { TechProjectViewModel(get()) }
    viewModel { MarsViewModel(get(),get()) }
    viewModel { FeaturesViewModel(get(),get()) }
    viewModel { ImageViewModel(get()) }
}
val dbModule : Module = module {
    single { AppDataBase.getInstanceDB(androidApplication()) }
}

val repositoryModule: Module = module {
single<NasaRepository> { NasaRepositoryImpl(get(),get()) }
}

val apiModule: Module = module {
    single<NasaService> { RetrofitBuilder.buildRetrofit() }
    single<NasaInteractor> { NasaInteractorImpl(get()) }
}

val appModules =
    listOf(viewModelModule, apiModule, repositoryModule, dbModule)