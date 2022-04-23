package com.example.restoranapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_urun_sayfasi.*
import org.json.JSONException
import org.json.JSONObject


class urun_sayfasi : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_urun_sayfasi, container, false)


            val url="http://192.168.43.114/restoranapp/tum_kisiler_arama.php"

            val istek : StringRequest = object : StringRequest(Method.POST,url, Response.Listener { cevap ->

                // Log.e("silmecevap2",cevap)

                try {

                    val jsonObject= JSONObject(cevap)
                    val isletmelerListe=jsonObject.getJSONArray("products")



                    for (i in 0 until isletmelerListe.length()){
                        val k=isletmelerListe.getJSONObject(i)
                        product_name.setText(k.getString("product_name"))
                        product_price.setText(k.getString("product_price") + " TL")
                        product_desc.setText(k.getString("product_desc"))

                    }

                }catch (e: JSONException){

                }



            }, Response.ErrorListener { e -> e.printStackTrace() }){
                override fun getParams(): MutableMap<String, String> {

                    val params = HashMap<String,String>()
                    params["product_id"]="1"

                    return params
                }
            }

            Volley.newRequestQueue(context).add(istek)




        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        constraintLayout8.setOnClickListener{
            val params = product_desc.layoutParams
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            product_desc.layoutParams = params
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            urun_sayfasi().apply {
                arguments = Bundle().apply {

                }
            }
    }
}