package com.example.databarang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.databarang.network.koneksi
import com.example.databarang.service.SessionPreferences
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_update_data.*
import kotlinx.android.synthetic.main.activity_update_data.btn_submit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var sessionPreferences: SessionPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btn_submit.setOnClickListener({
            val userName = et_username.text.toString()
            val passwword = et_password.text.toString()
            if (userName.isEmpty() || passwword.isEmpty()) {
                Toast.makeText(this, "Form tidak boleh kosong", Toast.LENGTH_LONG).show()
            } else {
                actionData(userName, passwword)
            }

        }
        btn_clean.setOnClickListener{
            formClear()
        }
        tv_disini.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })
    }
    fun actionData(username: String, password:String) {
        koneksi.service.register(username, password).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<???>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseAdmin>, response: Response<ResponseAdmin>) {
                if (response.isSuccessful){
                    val resbody = response.body()
                    val resStatus = resbody?.status
                    val resUserName = resbody?.data?.get(0)?.username
                    Log.d("pesan1", resUserName.toString())
                    if (resStatus == true) {
                        sessionPreferences = SessionPreferences(this@RegisterActivity)
                        sessionPreferences.actionLogin(resUserName.toString())
                        val i = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(i)
                        finish()
                    }else if(resStatus == false){
                        Toast.makeText(this@RegisterActivity,
                        "Username atau Password Anda Salah", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    fun formClear(){
        et_username.text.clear()
        et_password.text.clear()
    }
}

private fun Any.enqueue(callback: Callback<*>) {


}
