package com.example.restoranapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        btn_giris.setOnClickListener {
            if (mail_login.text.isEmpty() || parola_login.text.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Lütfen boş alan bırakmayınız...",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val url="http://192.168.43.114/restoranapp/costumer_uye_kontrol.php"

                val istek : StringRequest = object : StringRequest(Method.POST,url, Response.Listener { cevap ->

                    // Log.e("silmecevap2",cevap)

                    try {

                        val jsonObject= JSONObject(cevap)
                        val costumerListe=jsonObject.getJSONArray("costumers")

                        if (costumerListe.length()!=0){
                            val intent = Intent (this, sepetim::class.java)
                            intent.putExtra("costumer_id",costumerListe.getJSONObject(0).getInt("costumer_id").toString())
                            intent.putExtra("costumer_ad",costumerListe.getJSONObject(0).getString("costumer_ad").toString())
                            this?.startActivity(intent)
                        }

                    }catch (e: JSONException){

                    }



                }, Response.ErrorListener { e -> e.printStackTrace() }){
                    override fun getParams(): MutableMap<String, String> {

                        val params = HashMap<String,String>()
                        params["costumer_mail"]="3"
                        params["costumer_password"]="3"

                        return params
                    }
                }

                Volley.newRequestQueue(this).add(istek)

            }
        }



    }



}