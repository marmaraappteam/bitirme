package com.example.restoranapp


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
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
import kotlinx.android.synthetic.main.register.*
import kotlinx.coroutines.delay
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import kotlin.collections.HashMap

class browse_fragment : Fragment(), RecyclerViewAdapter.ClickListener {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var adapter2: RecyclerViewAdapter
    var listData: ArrayList<isletmeler> = ArrayList()
    var templistData: ArrayList<isletmeler> = ArrayList()
    var listDataforFavoriler: ArrayList<favorites> = ArrayList()
    var filterdataforPoint: ArrayList<String> = ArrayList()
    var filterdataforTur: ArrayList<String> = ArrayList()
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.browse_fragment,container,false)

        //buildDisplayData()
        // initRecyclerView(view)
        if (this@browse_fragment.arguments?.getStringArrayList("filtre_type")!=null){
            filterdataforPoint= this@browse_fragment.arguments?.getStringArrayList("filtre_type") as ArrayList<String>

        }
        if (this@browse_fragment.arguments?.getStringArrayList("filtre_tur")!=null){
            filterdataforTur= this@browse_fragment.arguments?.getStringArrayList("filtre_tur") as ArrayList<String>

        }

        listDataforFavoriler= this.arguments?.getSerializable("liste") as ArrayList<favorites>



        //changebuton2.setText(filterdata.get(0).toString())


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // tum_favoriler()

        tum_isletmeler(view)


        filter_button.setOnClickListener{
            val b = Bundle()
            val frag=filtre_fragmet()

            b.putStringArrayList("filtre_type", filterdataforPoint)
            b.putStringArrayList("filtre_tur", filterdataforTur)
            frag.arguments=b
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_tutucu, frag)
            transaction?.commit()


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
                initRecyclerView()

                return false
            }

        })

        changebuton2.setOnClickListener{

            val intent = Intent (getActivity(), konum_bilgisi::class.java)
            getActivity()?.startActivity(intent)
            // changebuton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

    }
    private fun tum_favoriler() {

        listDataforFavoriler.clear()
        val url2="http://192.168.43.114/restoranapp/tum_favoriler.php"

        val istek2 : StringRequest = object : StringRequest(Method.GET,url2, Response.Listener { cevap ->

            // Log.e("silmecevap2",cevap)


            try {

                val jsonObject=JSONObject(cevap)
                val favoritesListe=jsonObject.getJSONArray("favorites")

                for (i in 0 until favoritesListe.length()){
                    val k=favoritesListe.getJSONObject(i)
                    listDataforFavoriler.add(favorites(k.getInt("favorite_id"),k.getInt("kullanici_id"),k.getInt("isletme_id")))



                }

            }catch (e:JSONException){

            }



        }, Response.ErrorListener { e -> e.printStackTrace() }){}

        Volley.newRequestQueue(context).add(istek2)

    }
    private fun tum_isletmeler(view: View){


        val url="http://192.168.43.114/restoranapp/tum_isletmeler.php"

        val istek : StringRequest = object : StringRequest(Method.GET,url, Response.Listener { cevap ->

            // Log.e("silmecevap2",cevap)

            try {

                val jsonObject=JSONObject(cevap)
                val isletmelerListe=jsonObject.getJSONArray("isletmeler")


                var control=0
                for (i in 0 until isletmelerListe.length()){
                    val k=isletmelerListe.getJSONObject(i)
                    control=0
                    for (j in 0 until listDataforFavoriler.count()){

                        if (k.getInt("isletme_id")==listDataforFavoriler[j].isletme_id && 1==listDataforFavoriler[j].kullanici_id){
                            //templistData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur"),true))
                           // listData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur"),true))
                            control=1
                        }
                    }
                    if (control==1){
                        templistData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur"),true))
                         listData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur"),true))

                    }else{
                        templistData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur"),false))
                        listData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur"),false))

                    }

                    // templistData= templistData.distinctBy { it.isFavorite==true } as ArrayList<isletmeler>
                    //listData= listData.distinctBy { it.isletme_id } as ArrayList<isletmeler>

                    initRecyclerView()


                }

            }catch (e:JSONException){

            }



        }, Response.ErrorListener { e -> e.printStackTrace() }){}

        Volley.newRequestQueue(context).add(istek)
    }





    private fun initRecyclerView() {
        //  val recyclerView=view.findViewById<RecyclerView>(R.id.browse_recylview)

        browse_recylview.layoutManager=LinearLayoutManager(activity)

        (browse_recylview.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.VERTICAL

        adapter =  RecyclerViewAdapter(templistData,this)

        browse_recylview.adapter = adapter


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
            browse_fragment().apply {
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

    override fun onfvbuttonclick(fvModel: isletmeler) {
        Toast.makeText(context, "salih",Toast.LENGTH_SHORT).show()

        if (fvModel.isFavorite==false){

            for (i in 0 until templistData.size){
                if (templistData.get(i).isletme_id==fvModel.isletme_id){
                    templistData.get(i).isFavorite=true
                }

            }
            for (i in 0 until listData.size){
                if (listData.get(i).isletme_id==fvModel.isletme_id){
                    listData.get(i).isFavorite=true
                }

            }
            initRecyclerView()


            val url="http://192.168.43.114/restoranapp/insert_favorites.php"

            val istek : StringRequest = object : StringRequest(Method.POST,url, Response.Listener { cevap ->

                Log.e("silmecevap",cevap)


            }, Response.ErrorListener { e -> e.printStackTrace() }){
                override fun getParams(): MutableMap<String, String> {

                    val params = HashMap<String,String>()
                    params["kullanici_id"]="1"
                    params["isletme_id"]=fvModel.isletme_id.toString()

                    return params
                }
            }

            Volley.newRequestQueue(context).add(istek)


        }
        else if(fvModel.isFavorite==true){
            for (i in 0 until templistData.size){
                if (templistData.get(i).isletme_id==fvModel.isletme_id){
                    templistData.get(i).isFavorite=false
                }

            }
            for (i in 0 until listData.size){
                if (listData.get(i).isletme_id==fvModel.isletme_id){
                    listData.get(i).isFavorite=false
                }

            }
            initRecyclerView()

            val url="http://192.168.43.114/restoranapp/delete_favoriler.php"

            val istek : StringRequest = object : StringRequest(Method.POST,url, Response.Listener { cevap ->

                Log.e("silmecevap",cevap)


            }, Response.ErrorListener { e -> e.printStackTrace() }){
                override fun getParams(): MutableMap<String, String> {

                    val params = HashMap<String,String>()
                    params["kullanici_id"]="1"
                    params["isletme_id"]=fvModel.isletme_id.toString()

                    return params
                }
            }

            Volley.newRequestQueue(context).add(istek)

        }


    }




}