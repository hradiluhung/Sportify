package com.adiluhung.sportify.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adiluhung.sportify.core.domain.usecase.TeamUseCase

class HomeViewModel(teamUseCase: TeamUseCase) : ViewModel(){
    val team = teamUseCase.getAllTeam().asLiveData()
}