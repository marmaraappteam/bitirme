package com.example.restoranapp


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.example.myapplication.RecyclerViewAdapter
import kotlinx.android.synthetic.main.browse_fragment.*
import kotlinx.android.synthetic.main.discover_fragment.*
import kotlinx.android.synthetic.main.restoran_card.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import java.io.File
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import kotlin.collections.HashMap

class browse_fragment : Fragment(), RecyclerViewAdapter.ClickListener {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var adapter2: RecyclerViewAdapter
    val listData: ArrayList<isletmeler> = ArrayList()
    val templistData: ArrayList<isletmeler> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.browse_fragment,container,false)
        tum_isletmeler(view)
        //buildDisplayData()
        // initRecyclerView(view)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filter_button.setOnClickListener{
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.fragment_tutucu, filtre_fragmet())
            fr?.commit()
        }

        arama_butonu.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {


              templistData.clear()

                listData.filter {
                    (it.isletme_ad?.contains(p0!!)!!)

                }
                    .forEach { templistData.add(it) }
                initRecyclerView(view)

                return false
            }

        })

        changebuton2.setOnClickListener{

            val intent = Intent (getActivity(), konum_bilgisi::class.java)
            getActivity()?.startActivity(intent)
            // changebuton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

    }

   private fun tum_isletmeler(view: View){
        val url="http://192.168.43.114/restoranapp/tum_isletmeler.php"

        val istek : StringRequest = object : StringRequest(Method.GET,url, Response.Listener { cevap ->

           // Log.e("silmecevap2",cevap)

            try {

                val jsonObject=JSONObject(cevap)
                val isletmelerListe=jsonObject.getJSONArray("isletmeler")



                for (i in 0 until isletmelerListe.length()){
                    val k=isletmelerListe.getJSONObject(i)
                    templistData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur")))
                    listData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur")))

                    initRecyclerView(view)

                    //  val isletme_ad=k.getString("isletme_ad")
                   // val isletme_konum=k.getString("isletme_konum")
                    //val isletme_asaat=k.getString("isletme_asaat")
                    //val isletme_ksaat=k.getString("isletme_ksaat")
                    //val isletme_point=k.getString("isletme_point")
                }

            }catch (e:JSONException){

            }



        }, Response.ErrorListener { e -> e.printStackTrace() }){}

        Volley.newRequestQueue(context).add(istek)
    }



    private fun initRecyclerView(view: View) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.browse_recylview)

        recyclerView.layoutManager=LinearLayoutManager(activity)

        (recyclerView.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.VERTICAL

        adapter =  RecyclerViewAdapter(templistData,this)

        recyclerView.adapter = adapter

    }

   /* private fun buildDisplayData() {
        listData.add(urunler(1,"Çınar Pide Fırın","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(2,"Simit Sarayı","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(3,"Vedat Usta","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(4,"Waffle","Yarın","2.5 km","25","Son 5 Ürün"))


    }*/

    companion object{
        @JvmStatic
        fun newInstance() =
            discover_fragment().apply {
                arguments=Bundle().apply {

                }
            }
    }



    override fun onItemClick(dataModel: isletmeler) {
        val b = Bundle()
        val frag=restoran_sayfa_fragment()

        b.putString("isletme_id", dataModel.isletme_id.toString())
        frag.arguments=b
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_tutucu, frag)
        transaction?.disallowAddToBackStack()
        transaction?.commit()

    //val intent = Intent (getActivity(), restoran_sayfa::class.java)
        //intent.putExtra("urun_id",dataModel.urun_id.toString())
        //getActivity()?.startActivity(intent)
    }



}