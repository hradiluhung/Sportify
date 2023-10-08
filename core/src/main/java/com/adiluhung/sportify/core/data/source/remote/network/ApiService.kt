package com.adiluhung.sportify.core.data.source.remote.network

import com.adiluhung.sportify.core.data.source.remote.response.ListPlayerResponse
import com.adiluhung.sportify.core.data.source.remote.response.ListTeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search_all_teams.php?l=English%20Premier%20League")
    suspend fun getTeams(): ListTeamResponse

    @GET("searchplayers.php")
    suspend fun getTeamPlayers(@Query("t") teamName: String): ListPlayerResponse

}