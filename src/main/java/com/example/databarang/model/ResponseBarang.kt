package com.example.databarang.model

import com.google.gson.annotations.SerializedName
import java.sql.ClientInfoStatus

data class ResponseBarang(
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class DataItem(
    @field:SerializedName("nama_barang")
    val namabarang: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("jumlah_barang")
    val jumlahbarang: String? = null
)