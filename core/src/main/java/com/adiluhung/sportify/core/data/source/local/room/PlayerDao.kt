package com.adiluhung.sportify.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.adiluhung.sportify.core.data.source.local.entity.PlayerEntity
import com.adiluhung.sportify.core.data.source.local.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao{
    @Query("SELECT * FROM player")
    fun getTeamPlayers(): Flow<List<PlayerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: List<PlayerEntity>)
}