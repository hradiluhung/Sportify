package com.adiluhung.sportify.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListPlayerResponse(
    @field:SerializedName("player")
    val player: List<PlayerResponse>
)
