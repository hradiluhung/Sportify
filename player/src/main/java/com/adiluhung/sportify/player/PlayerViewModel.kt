package com.adiluhung.sportify.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adiluhung.sportify.core.domain.usecase.PlayerUseCase

class PlayerViewModel(playerUseCase: PlayerUseCase) : ViewModel() {
    val player = playerUseCase.getTeamPlayers("Arsenal").asLiveData()
}