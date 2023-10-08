package com.adiluhung.sportify.core.domain.repository

import com.adiluhung.sportify.core.data.Resource
import com.adiluhung.sportify.core.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface IPlayerRepository {
    fun getTeamPlayers(teamName: String) : Flow<Resource<List<Player>>>
}