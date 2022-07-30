package com.mahmoudsalah.shoptask.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    private const val URL = "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/default/"

    fun getAPIService() : ItemAPI{
        return Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()
            .create(ItemAPI::class.java)
    }
}