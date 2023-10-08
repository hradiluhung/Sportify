package com.adiluhung.sportify.di

import com.adiluhung.sportify.core.domain.usecase.PlayerInteractor
import com.adiluhung.sportify.core.domain.usecase.PlayerUseCase
import com.adiluhung.sportify.core.domain.usecase.TeamInteractor
import com.adiluhung.sportify.core.domain.usecase.TeamUseCase
import com.adiluhung.sportify.detail.DetailViewModel
import com.adiluhung.sportify.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val useCaseModule = module {
    factory<TeamUseCase> { TeamInteractor(get()) }
    factory<PlayerUseCase> { PlayerInteractor(get()) }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}