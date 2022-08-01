package com.allendevbang.weathertotravel

import com.allendevbang.weathertotravel.api.KtorClient
import com.allendevbang.weathertotravel.api.WeatherRepo
import com.allendevbang.weathertotravel.api.WeatherRepoImpl
import com.allendevbang.weathertotravel.usecase.WeatherUseCaseImpl
import com.allendevbang.weathertotravel.viewmodel.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherToTravelModule = module {
    single {
        KtorClient()
    }
    single<WeatherRepo> {
        WeatherRepoImpl(get<KtorClient>().getClient())
    }
    factory {
        WeatherUseCaseImpl(get())
    }
    viewModel {
        MainScreenViewModel(get())
    }
}