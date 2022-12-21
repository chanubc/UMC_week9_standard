package com.example.umc_week9_standard

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/15052413/v1/")
    fun getCheckMail(
        @Query("currentCount") calendarId:Int,
//        @Query("perPage") schedule:Int,
//        @Query("serviceKey") serviceKey:String
    ):Call<Response>
}