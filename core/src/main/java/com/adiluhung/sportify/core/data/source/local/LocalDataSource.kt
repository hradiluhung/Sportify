package com.adiluhung.sportify.core.data.source.local

import com.adiluhung.sportify.core.data.source.local.entity.PlayerEntity
import com.adiluhung.sportify.core.data.source.local.entity.TeamEntity
import com.adiluhung.sportify.core.data.source.local.room.PlayerDao
import com.adiluhung.sportify.core.data.source.local.room.TeamDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val teamDao: TeamDao, private val playerDao: PlayerDao) {
    fun getAllTeams(): Flow<List<TeamEntity>> = teamDao.getAllTeam()

    fun getFavoriteTeams(): Flow<List<TeamEntity>> = teamDao.getFavoriteTeam()

    suspend fun insertTeams(teamList: List<TeamEntity>) = teamDao.insertTeam(teamList)

    fun setFavoriteTeam(team: TeamEntity, newState: Boolean) {
        team.isFavorite = newState
        teamDao.updateFavoriteTeam(team)
    }

    fun getTeamPlayers(): Flow<List<PlayerEntity>> = playerDao.getTeamPlayers()

    suspend fun insertPlayers(playerList: List<PlayerEntity>) = playerDao.insertTeam(playerList)
}