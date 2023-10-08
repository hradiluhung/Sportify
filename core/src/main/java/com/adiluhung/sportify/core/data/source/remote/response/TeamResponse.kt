package com.adiluhung.sportify.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @field:SerializedName("idTeam")
    var idTeam: String,

    @field:SerializedName("strTeam")
    var strTeam: String,

    @field:SerializedName("strTeamShort")
    var strTeamShort: String,

    @field:SerializedName("strDescriptionEN")
    var strDescriptionEN: String,

    @field:SerializedName("strStadium")
    var strStadium: String,

    @field:SerializedName("strStadiumThumb")
    var strStadiumThumb: String,

    @field:SerializedName("strTeamBadge")
    var strTeamBadge: String,

    @field:SerializedName("strKitColour1")
    var strKitColour1: String,
)