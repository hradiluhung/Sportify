package com.adiluhung.sportify.detail

import androidx.lifecycle.ViewModel
import com.adiluhung.sportify.core.domain.model.Team
import com.adiluhung.sportify.core.domain.usecase.TeamUseCase

class DetailViewModel(private val teamUseCase: TeamUseCase) : ViewModel() {
    fun setFavoriteTeam(team: Team, newStatus: Boolean) =
         teamUseCase.setFavoriteTeam(team, newStatus)
}