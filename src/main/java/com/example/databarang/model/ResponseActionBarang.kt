package com.example.databarang.model

import com.google.gson.annotations.SerializedName
import java.sql.ClientInfoStatus

data class ResponseActionBarang(
    @field:SerializedName("pesan")
    val pesan: Any? = null,

    @field:SerializedName("data")
    val data: List<Boolean?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)