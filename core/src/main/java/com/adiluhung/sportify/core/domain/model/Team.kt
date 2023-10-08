package com.adiluhung.sportify.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val idTeam: String,
    val strTeam: String,
    val strTeamShort: String,
    val strDescriptionEN: String,
    val strStadium: String,
    val strStadiumThumb: String,
    val strTeamBadge: String,
    val strKitColour1: String,
    val isFavorite: Boolean = false
) : Parcelable