package com.kopikode.jadwalbola

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface FootballApiService {
    @Headers("X-Auth-Token: 98c8265c8a1d48d1ae13338146382661")
    @GET("v4/matches")
    suspend fun getUpcomingMatches(): MatchResponse

    // Endpoint baru untuk ambil detail pertandingan tertentu
    @Headers("X-Auth-Token: 98c8265c8a1d48d1ae13338146382661")
    @GET("v4/matches/{id}")
    suspend fun getMatchDetail(@Path("id") matchId: Int): Match
}