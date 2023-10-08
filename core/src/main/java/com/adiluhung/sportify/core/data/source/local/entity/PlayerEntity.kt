package com.adiluhung.sportify.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class PlayerEntity(
    @PrimaryKey
    @ColumnInfo(name = "idPlayer")
    var idPlayer: String,

    @ColumnInfo(name = "strPlayer")
    var strPlayer : String,

    @ColumnInfo(name = "strDescriptionEN")
    var strDescriptionEN : String?,

    @ColumnInfo(name = "strThumb")
    var strThumb : String?,

    @ColumnInfo(name = "dateBorn")
    var dateBorn : String?,
)