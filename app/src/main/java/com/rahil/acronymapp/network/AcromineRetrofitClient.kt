package com.rahil.acronymapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AcromineRetrofitClient {
        private const val BASE_URL = "http://www.nactem.ac.uk/"


    private val builder = OkHttpClient.Builder().retryOnConnectionFailure(true)


    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val instance: AcromineApiService = retrofit.create(AcromineApiService::class.java)
    }