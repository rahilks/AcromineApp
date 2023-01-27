package com.rahil.acronymapp.network

import com.rahil.acronymapp.data.Acromine
import com.rahil.acronymapp.data.AcromineResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AcromineApiService {

    @GET("software/acromine/dictionary.py")
    fun getAcronyms(@Query("sf") sf: String) : Call<List<Acromine>>
}
