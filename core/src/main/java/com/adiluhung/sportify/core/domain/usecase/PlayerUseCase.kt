package com.adiluhung.sportify.core.domain.usecase

import com.adiluhung.sportify.core.data.Resource
import com.adiluhung.sportify.core.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface PlayerUseCase {
    fun getTeamPlayers(teamName: String): Flow<Resource<List<Player>>>
}