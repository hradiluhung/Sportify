package com.adiluhung.sportify.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    var idPlayer: String,
    var strPlayer: String,
    var strDescriptionEN: String,
    var strThumb: String,
    var dateBorn: String,
) : Parcelable

