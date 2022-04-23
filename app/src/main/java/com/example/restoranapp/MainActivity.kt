package com.example.restoranapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.restoranapp.R.id.custom_toolbar
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.browse_fragment.*

import kotlinx.android.synthetic.main.custom_toolbar.*
import retrofit2.Call
import retrofit2.Callback
import java.io.File
import kotlinx.android.synthetic.main.custom_toolbar.custom_toolbar as custom_toolbar1

class MainActivity : AppCompatActivity() {


    private lateinit var tempFragment:Fragment

    private lateinit var ulkelerliste:ArrayList<ulkeler>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // supportActionBar?.hide() //toolbarı gizler
        // supportActionBar?.setTitle("İsrafa Son")
      // setSupportActionBar(custom_toolbar as androidx.appcompat.widget.Toolbar?)

        textView21.setOnClickListener{

            val intent = Intent (this, sepetim::class.java)

            startActivity(intent)
        }


        supportFragmentManager.beginTransaction().add(R.id.fragment_tutucu,discover_fragment()).commit()

        bottom_navigation.setOnNavigationItemSelectedListener {menuItem->
            if (menuItem.itemId==R.id.bottom_discover)
            {
                Toast.makeText(applicationContext,"birinci",Toast.LENGTH_SHORT).show()
                tempFragment=discover_fragment()
            }
            if (menuItem.itemId==R.id.bottom_browse)
            {
                Toast.makeText(applicationContext,"ikinci",Toast.LENGTH_SHORT).show()
                tempFragment=browse_fragment()
            }
            if (menuItem.itemId==R.id.bottom_more)
            {
                Toast.makeText(applicationContext,"ucuncu",Toast.LENGTH_SHORT).show()
                tempFragment=more_fragment()
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_tutucu,tempFragment).commit()

            true

        }
    }
         fun kisi_sil(){
        //File("C:\\Users\\salihokur\\Desktop\\bitirmekotlin\\app\\src\\main\\java\\com\\example\\restoranapp\\bezkoder.txt").writeText("asdasdasd")

        Toast.makeText(applicationContext,"ucuncu",Toast.LENGTH_SHORT).show()
        val url="http://192.168.43.114/restoranapp/delete_kisiler.php"

        val istek : StringRequest = object : StringRequest(Method.POST,url, Response.Listener { cevap ->

            Log.e("silmecevap",cevap)


        }, Response.ErrorListener { e -> e.printStackTrace() }){
            override fun getParams(): MutableMap<String, String> {

                val params = HashMap<String,String>()
                params["costumer_id"]="3"

                return params
            }
        }

        Volley.newRequestQueue(this@MainActivity).add(istek)
        Toast.makeText(applicationContext,"uasdasdmaşsdmşas",Toast.LENGTH_SHORT).show()
    }

         fun tum_isletmeler(){
        Toast.makeText(applicationContext,"ucuncu",Toast.LENGTH_SHORT).show()
        val url="http://sadeceseninicintiklagor.cf"

        val istek : StringRequest = object : StringRequest(Method.POST,url, Response.Listener { cevap ->

            Log.e("silmecevap",cevap)


        }, Response.ErrorListener { e -> e.printStackTrace() }){
            override fun getParams(): MutableMap<String, String> {

                val params = HashMap<String,String>()
                params["costumer_id"]="3"

                return params
            }
        }

        Volley.newRequestQueue(this@MainActivity).add(istek)
        Toast.makeText(applicationContext,"uasdasdmaşsdmşas",Toast.LENGTH_SHORT).show()
    }






}