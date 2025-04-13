package com.example.app

import retrofit2.http.GET

interface WebsiteAPIService {
    @GET("websites_info.json")
    suspend fun getWebsites(): List<Website>
}