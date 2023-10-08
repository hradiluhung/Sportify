package com.adiluhung.sportify.core.data

import com.adiluhung.sportify.core.data.source.local.LocalDataSource
import com.adiluhung.sportify.core.data.source.remote.RemoteDataSource
import com.adiluhung.sportify.core.data.source.remote.network.ApiResponse
import com.adiluhung.sportify.core.data.source.remote.response.TeamResponse
import com.adiluhung.sportify.core.domain.model.Team
import com.adiluhung.sportify.core.domain.repository.ITeamRepository
import com.adiluhung.sportify.core.utils.AppExecutors
import com.adiluhung.sportify.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITeamRepository {
    override fun getAllTeam(): Flow<Resource<List<Team>>> =
        object : NetworkBoundResource<List<Team>, List<TeamResponse>>() {
            override fun loadFromDB(): Flow<List<Team>> {
                return localDataSource.getAllTeams().map {
                    DataMapper.mapTeamEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Team>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TeamResponse>>> =
                remoteDataSource.getAllTeams()

            override suspend fun saveCallResult(data: List<TeamResponse>) {
                val teamList = DataMapper.mapTeamResponsesToEntities(data)
                localDataSource.insertTeams(teamList)
            }
        }.asFLow()


    override fun getFavoriteTeam(): Flow<List<Team>> {
        return localDataSource.getFavoriteTeams().map {
            DataMapper.mapTeamEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTeam(team: Team, state: Boolean) {
        val teamEntity = DataMapper.mapTeamDomainToEntities(team)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTeam(teamEntity, state) }
    }
}