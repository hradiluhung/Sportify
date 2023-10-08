package com.adiluhung.sportify.core.data

import com.adiluhung.sportify.core.data.source.local.LocalDataSource
import com.adiluhung.sportify.core.data.source.remote.RemoteDataSource
import com.adiluhung.sportify.core.data.source.remote.network.ApiResponse
import com.adiluhung.sportify.core.data.source.remote.response.PlayerResponse
import com.adiluhung.sportify.core.domain.model.Player
import com.adiluhung.sportify.core.domain.repository.IPlayerRepository
import com.adiluhung.sportify.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlayerRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IPlayerRepository {
    override fun getTeamPlayers(teamName: String): Flow<Resource<List<Player>>> {
        return object : NetworkBoundResource<List<Player>, List<PlayerResponse>>() {
            override fun loadFromDB(): Flow<List<Player>> {
                return localDataSource.getTeamPlayers().map {
                    DataMapper.mapPlayerEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<PlayerResponse>>> =
                remoteDataSource.getTeamPlayers(teamName)

            override suspend fun saveCallResult(data: List<PlayerResponse>) {
                val playerList = DataMapper.mapPlayerResponsesToEntities(data)
                localDataSource.insertPlayers(playerList)
            }

            override fun shouldFetch(data: List<Player>?): Boolean = data.isNullOrEmpty()


        }.asFLow()
    }
}