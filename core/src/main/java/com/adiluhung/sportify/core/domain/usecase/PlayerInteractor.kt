package com.adiluhung.sportify.core.domain.usecase

import com.adiluhung.sportify.core.domain.repository.IPlayerRepository

class PlayerInteractor(private val playerRepository: IPlayerRepository) : PlayerUseCase {
    override fun getTeamPlayers(teamName: String) = playerRepository.getTeamPlayers(teamName)
}