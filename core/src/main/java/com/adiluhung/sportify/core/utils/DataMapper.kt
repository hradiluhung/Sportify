package com.adiluhung.sportify.core.utils

import com.adiluhung.sportify.core.data.source.local.entity.PlayerEntity
import com.adiluhung.sportify.core.data.source.local.entity.TeamEntity
import com.adiluhung.sportify.core.data.source.remote.response.PlayerResponse
import com.adiluhung.sportify.core.data.source.remote.response.TeamResponse
import com.adiluhung.sportify.core.domain.model.Player
import com.adiluhung.sportify.core.domain.model.Team

object DataMapper {

    fun mapTeamResponsesToEntities(input: List<TeamResponse>): List<TeamEntity> {
        val teamList = ArrayList<TeamEntity>()

        input.map {
            val team = TeamEntity(
                idTeam = it.idTeam,
                strTeam = it.strTeam,
                strTeamShort = it.strTeamShort,
                strDescriptionEN = it.strDescriptionEN,
                strStadium = it.strStadium,
                strStadiumThumb = it.strStadiumThumb,
                strTeamBadge = it.strTeamBadge,
                strKitColour1 = it.strKitColour1,
                isFavorite = false
            )

            teamList.add(team)
        }

        return teamList
    }

    fun mapTeamEntitiesToDomain(input: List<TeamEntity>): List<Team> =
        input.map {
            Team(
                idTeam = it.idTeam,
                strTeam = it.strTeam,
                strTeamShort = it.strTeamShort,
                strDescriptionEN = it.strDescriptionEN,
                strStadium = it.strStadium,
                strStadiumThumb = it.strStadiumThumb,
                strTeamBadge = it.strTeamBadge,
                strKitColour1 = it.strKitColour1,
                isFavorite = it.isFavorite
            )
        }

    fun mapTeamDomainToEntities(input: Team) = TeamEntity(
        idTeam = input.idTeam,
        strTeam = input.strTeam,
        strTeamShort = input.strTeamShort,
        strDescriptionEN = input.strDescriptionEN,
        strStadium = input.strStadium,
        strStadiumThumb = input.strStadiumThumb,
        strTeamBadge = input.strTeamBadge,
        strKitColour1 = input.strKitColour1,
        isFavorite = input.isFavorite
    )

    fun mapPlayerResponsesToEntities(input: List<PlayerResponse>): List<PlayerEntity> {
        val playerList = ArrayList<PlayerEntity>()
        input.map {
            val player = PlayerEntity(
                idPlayer = it.idPlayer,
                strPlayer = it.strPlayer,
                strDescriptionEN = it.strDescriptionEN,
                strThumb = it.strThumb,
                dateBorn = it.dateBorn
            )

            playerList.add(player)
        }
        return playerList
    }

    fun mapPlayerEntitiesToDomain(input: List<PlayerEntity>): List<Player> =
        input.map {
            Player(
                idPlayer = it.idPlayer,
                strPlayer = it.strPlayer,
                strDescriptionEN = it.strDescriptionEN ?: "",
                strThumb = it.strThumb ?: "",
                dateBorn = it.dateBorn ?: ""
            )
        }

    fun mapPlayerDomainToEntities(input: Player) = PlayerEntity(
        idPlayer = input.idPlayer,
        strPlayer = input.strPlayer,
        strDescriptionEN = input.strDescriptionEN,
        strThumb = input.strThumb,
        dateBorn = input.dateBorn
    )

}