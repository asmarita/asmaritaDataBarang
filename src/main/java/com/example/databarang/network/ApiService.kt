package com.example.databarang.network

import com.example.databarang.model.ResponseActionBarang
import com.example.databarang.model.ResponseBarang
import com.example.databarang.model.ResponseUsersItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("read.php")
    fun getBarang() : Call<ResponseBarang>

    @FormUrlEncoded
    @POST("create.php")

    fun  insertBarang(
        @Field("nama_barang") namaBarang: String?,
        @Field("jumlah_barang") jumlahBarang: String?
    ) : Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("update.php")

    fun updateBarang(
        @Field("id") id: String?,
        @Field("nama_barang") namaBarang: String?,
        @Field("jumlah_barang") jumlahBarang: String?
    ) : Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("delete.php")

    fun deleteBarang(
        @Field("id")id: String?
    ): Call<ResponseActionBarang>

    fun register(username: String, password: String): Any


}