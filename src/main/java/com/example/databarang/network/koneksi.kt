package com.example.databarang.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class koneksi {
    companion object {
        val baseUrl = "http://192.168.43.207/dabar/api/"
        var retrofit: Retrofit? = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var service: ApiService = retrofit!!.create(ApiService::class.java)
    }
}