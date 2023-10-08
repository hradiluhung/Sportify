package com.adiluhung.sportify.core.domain.repository

import com.adiluhung.sportify.core.data.Resource
import com.adiluhung.sportify.core.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface ITeamRepository {
    fun getAllTeam() : Flow<Resource<List<Team>>>

    fun getFavoriteTeam(): Flow<List<Team>>

    fun setFavoriteTeam(team: Team, state: Boolean)
}