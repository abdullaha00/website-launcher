package com.example.app

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/davidjarvis-TE/414edf2b4e878ab7ba1bf6bb1291a89e/raw/7537d5a0a37120e4a7127cc8f65f5265e723ff7b/"

    private val okHTTP = OkHttpClient.Builder()
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val websiteAPIService: WebsiteAPIService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHTTP)
            .build()
            .create(WebsiteAPIService::class.java)
    }
}