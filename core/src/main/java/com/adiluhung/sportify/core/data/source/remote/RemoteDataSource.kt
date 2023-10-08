package com.adiluhung.sportify.core.data.source.remote

import android.util.Log
import com.adiluhung.sportify.core.data.source.remote.network.ApiResponse
import com.adiluhung.sportify.core.data.source.remote.network.ApiService
import com.adiluhung.sportify.core.data.source.remote.response.PlayerResponse
import com.adiluhung.sportify.core.data.source.remote.response.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllTeams(): Flow<ApiResponse<List<TeamResponse>>> {
        return flow {
            try {
                val response = apiService.getTeams()
                val dataArray = response.teams
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.teams))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTeamPlayers(teamName: String): Flow<ApiResponse<List<PlayerResponse>>> {
        return flow {
            try {
                val response = apiService.getTeamPlayers(teamName)
                val dataArray = response.player
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.player))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}