package com.adiluhung.sportify.favorite

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val favoriteModule = module {
    viewModelOf(::FavoriteViewModel)
}