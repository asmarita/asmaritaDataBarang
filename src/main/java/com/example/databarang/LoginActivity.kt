package com.example.databarang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.databarang.model.ResponseUsers
import com.example.databarang.network.koneksi
import com.example.databarang.service.SessionPreferences
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_clean
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_login.et_username
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_update_data.*
import kotlinx.android.synthetic.main.activity_update_data.btn_submit
import javax.security.auth.callback.Callback

private val Any.text: Any
    get() {
        TODO("Not yet implemented")
    }

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_submit.setOnClickListener{
            val userName = et_username.text.toString()
            val  password = et_password.text.toString()

            if(userName.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "Form tidak boleh kosong!", Toast.LENGTH_LONG).show()

            }else{
                actionData(userName, password)
            }
        }

        btn_clean.setOnClickListener{
            formClear()
        }

        tv_disini.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    fun actionData(username : String, password: String){
        koneksi.service.logIn(username, password).enqueue(object : Callback<ResponseUsers>) {
            if(response.isSuccesful){
                val resbody = response.body()
                val resStatus = resbody?.data?.get(0)?.username
                Log.d("pesan", resUserName.toString())

                if (resStatus == true){
                    var sessionPreferences = SessionPreferences(this@LoginActivity)
                    sessionPreferences.actionLogin(resUserName.toString())
                    val i = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }else if(resStatus==false){
                    Toast.makeText(this@LoginActivity, "Username atau Password Anda Salah", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

fun formClear(){
    et_username.text.clear()
    et_password.text.clear()
}
