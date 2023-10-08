package com.adiluhung.sportify.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adiluhung.sportify.core.domain.usecase.TeamUseCase

class FavoriteViewModel(teamUseCase: TeamUseCase): ViewModel() {
    val favoriteTeam = teamUseCase.getFavoriteTeam().asLiveData()
}