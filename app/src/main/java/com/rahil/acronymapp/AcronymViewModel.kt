package com.rahil.acronymapp


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahil.acronymapp.data.Acromine
import com.rahil.acronymapp.data.LongForm
import com.rahil.acronymapp.network.AcromineRetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AcronymViewModel : ViewModel()  {
    var errorMessage: String = ""
    var showDialog = MutableStateFlow(false)

    private val _data = MutableLiveData<List<LongForm>>()
    val data: LiveData<List<LongForm>> = _data


    fun fetchData(sf: String) {
        Log.e("mytag"," fetchData "+ sf)

        val api = AcromineRetrofitClient.instance // RetrofitClient is class which contains the retrofit client
        val call = api.getAcronyms(sf) // getData is the endpoint which is returning the data
        Log.e("mytag", "call url: " + call.request().url().toString())

        call.enqueue(object : Callback<List<Acromine>> {
            override fun onResponse(call: Call<List<Acromine>>, response: Response<List<Acromine>>) {
                Log.e("mytag", "onResponse" )
                if (response.isSuccessful) {
                    Log.e("mytag", " response.body() "+ response.body() )
                    val list =  response.body()
                    if(list != null && list.isNotEmpty()) {
                        _data.value = list[0].lfs
                    }
                    else {
                        errorMessage = "Data is empty"
                        _data.value = null
                        showDialog.value = true
                    }
                }
            }

            override fun onFailure(call: Call<List<Acromine>>, t: Throwable) {
                Log.e("mytag"," fetchData error "+ t)
                errorMessage = t.message.toString()
                showDialog.value = true
            }
        })
    }
}