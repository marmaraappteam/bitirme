package com.example.restoranapp

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.browse_fragment.*
import kotlinx.android.synthetic.main.register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)



        btn_register.setOnClickListener{
           if (mail_register.text.isEmpty() || kd_register.text.isEmpty() || parola_register.text.isEmpty()|| parolat_register.text.isEmpty()){
               Toast.makeText(applicationContext,"Lütfen boş alan bırakmayınız...", Toast.LENGTH_SHORT).show()
           }
           else if (parola_register.text!=parolat_register.text){
                Toast.makeText(applicationContext,"Şifreler uyuşmuyor", Toast.LENGTH_SHORT).show()
            }
            else{
               Toast.makeText(applicationContext,"ucuncu",Toast.LENGTH_SHORT).show()
               val url="http://192.168.43.114/restoranapp/insert_costumer.php"

               val istek : StringRequest = object : StringRequest(Method.POST,url, Response.Listener { cevap ->

                   Log.e("silmecevap",cevap)


               }, Response.ErrorListener { e -> e.printStackTrace() }){
                   override fun getParams(): MutableMap<String, String> {

                       val params = HashMap<String,String>()
                       params["costumer_mail"]=mail_register.text.toString()
                       params["costumer_ad"]=kd_register.text.toString()
                       params["costumer_password"]=parola_register.text.toString()

                       return params
                   }
               }

               Volley.newRequestQueue(this).add(istek)
               Toast.makeText(applicationContext,"uasdasdmaşsdmşas",Toast.LENGTH_SHORT).show()
            //   val intent = Intent (this, LoginActivity::class.java)
           //    intent.putExtra("urun_id",dataModel.urun_id.toString())
             //  this?.startActivity(intent)
            }

        }
    }
}