package com.kopikode.jadwalbola

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    val matches: List<Match>
)

data class Match(@SerializedName("id")
    val id: Int,
    val utcDate: String,
    val status: String,
    val homeTeam: Team, // Harus 'homeTeam'
    val awayTeam: Team, // Harus 'awayTeam'
    val score: Score?,
    val goals: List<Goal>?
)

data class Goal(
    val minute: Int?,
    val injuryTime: Int?,
    val type: String?,
    val scorer: Scorer?,
    val assist: Scorer?, // Tambahkan assist jika perlu
    val team: TeamGoal?  // Menggunakan TeamGoal untuk mengambil ID/Nama tim
)

data class Team(
    val name: String,   // Harus 'name'
    val crest: String?  // Harus 'crest'
)

data class Score(
    val fullTime: TimeScore
)

data class TeamGoal(
    val id: Int,
    val name: String
)

data class TimeScore(
    val home: Int?,
    val away: Int?
)

data class Scorer(val name: String)
data class TeamId(val name: String)