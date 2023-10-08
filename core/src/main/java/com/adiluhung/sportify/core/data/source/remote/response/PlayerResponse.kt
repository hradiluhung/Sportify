package com.adiluhung.sportify.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @field:SerializedName("idPlayer")
    var idPlayer: String,

    @field:SerializedName("strPlayer")
    var strPlayer: String,

    @field:SerializedName("strDescriptionEN")
    var strDescriptionEN: String,

    @field:SerializedName("strThumb")
    var strThumb: String,

    @field:SerializedName("dateBorn")
    var dateBorn: String,
)
