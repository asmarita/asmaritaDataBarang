package com.example.databarang

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databarang.adapter.ListContent
import com.example.databarang.model.ResponseActionBarang
import com.example.databarang.model.ResponseBarang
import com.example.databarang.network.koneksi
import kotlinx.android.synthetic.main.activity_insert_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title= "INSERT DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener{
            val etNamaBarang = et_nama_barang.text
            val etJumlahBarang = et_jumlah_barang.text
            if(etJumlahBarang.isEmpty()){
                Toast.makeText(this@InsertDataActivity, "Jumlah Barang Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else if(etNamaBarang.isEmpty()){
                Toast.makeText(this@InsertDataActivity, "Nama Barang Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else{
                actionData(etNamaBarang.toString(), etJumlahBarang.toString())
            }
        }

        btn_clean.setOnClickListener{
            formClear()
        }

        getData()
    }


    private fun formClear() {
        et_nama_barang.text.clear()
        et_jumlah_barang.text.clear()
    }

    private fun actionData(namaBarang : String, jumlahBarang : String) {
        koneksi.service.insertBarang(namaBarang, jumlahBarang).enqueue(object : Callback<ResponseActionBarang>{
            override fun onFailure(call : Call<ResponseActionBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun  onResponse(
                call: Call<ResponseActionBarang>,
                response: Response<ResponseActionBarang>
            ){
                if(response.isSuccessful){
                    Toast.makeText(this@InsertDataActivity, "data berhasil disimpan", Toast.LENGTH_LONG).show()
                    formClear()
                    getData()
                }
            }
        })
    }

     fun getData() {
       koneksi.service.getBarang().enqueue(object : Callback<ResponseBarang> {
           override fun onFailure(call: Call<ResponseBarang>, t:Throwable){
               Log.d("pesan1", t.localizedMessage)
           }

           override fun onResponse(
               call: Call<ResponseBarang>,
                response: Response<ResponseBarang>
           ){
               if(response.isSuccessful){
                   val dataBody = response.body()
                   val datacontent = dataBody!!.data
                   val rvAdapter = ListContent(datacontent, this@InsertDataActivity)

                   rv_data_barang.apply {
                       adapter = rvAdapter
                       layoutManager = LinearLayoutManager(this@InsertDataActivity)
                   }
               }
           }
       })

    }

}