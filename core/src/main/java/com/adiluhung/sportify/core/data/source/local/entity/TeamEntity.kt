package com.adiluhung.sportify.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey
    @ColumnInfo(name = "idTeam")
    var idTeam: String,

    @ColumnInfo(name = "strTeam")
    var strTeam : String,

    @ColumnInfo(name = "strTeamShort")
    var strTeamShort : String,

    @ColumnInfo(name = "strDescriptionEN")
    var strDescriptionEN: String,

    @ColumnInfo(name= "strStadium")
    var strStadium: String,

    @ColumnInfo(name = "strStadiumThumb")
    var strStadiumThumb: String,

    @ColumnInfo(name = "strTeamBadge")
    var strTeamBadge: String,

    @ColumnInfo(name = "strKitColour1")
    var strKitColour1: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite : Boolean = false
)
