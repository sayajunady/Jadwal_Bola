package com.kopikode.jadwalbola

data class MatchResponse(
    val matches: List<Match>
)

data class Match(
    val utcDate: String,
    val status: String,
    val homeTeam: Team, // Harus 'homeTeam'
    val awayTeam: Team, // Harus 'awayTeam'
    val score: Score?
)

data class Team(
    val name: String,   // Harus 'name'
    val crest: String?  // Harus 'crest'
)

data class Score(
    val fullTime: TimeScore
)

data class TimeScore(
    val home: Int?,
    val away: Int?
)