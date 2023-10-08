package com.adiluhung.sportify.core.domain.usecase

import com.adiluhung.sportify.core.domain.model.Team
import com.adiluhung.sportify.core.domain.repository.ITeamRepository

class TeamInteractor(private val teamRepository: ITeamRepository) : TeamUseCase {
    override fun getAllTeam() = teamRepository.getAllTeam()

    override fun getFavoriteTeam() = teamRepository.getFavoriteTeam()

    override fun setFavoriteTeam(team: Team, state: Boolean) =
        teamRepository.setFavoriteTeam(team, state)
}