package com.example.restoranapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.example.myapplication.RecyclerViewAdapter
import kotlinx.android.synthetic.main.discover_fragment.*
import kotlinx.android.synthetic.main.more_fragment.*
import org.json.JSONException
import org.json.JSONObject

class more_fragment : Fragment(), RecyclerViewAdapter.ClickListener {

    private lateinit var adapter: RecyclerViewAdapter


    val listData: ArrayList<isletmeler> = ArrayList()
    var listDataforFavoriler: ArrayList<favorites> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.more_fragment,container,false)
       // buildDisplayData()


        listDataforFavoriler= this.arguments?.getSerializable("liste") as ArrayList<favorites>
        favori_isletmeler()

        return view
    }

    private fun initRecyclerView() {


        rv_favorite.layoutManager=LinearLayoutManager(activity)

        (rv_favorite.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.VERTICAL

        adapter =  RecyclerViewAdapter(listData,this)

        rv_favorite.adapter = adapter

    }

    private fun buildDisplayData() {
        listData.add(isletmeler(1,"BMW","tomorrow","2.5 km","25","5 left","Fırın",false))
        listData.add(isletmeler(2,"veyt","tomorrow","2.5 km","25","5 left","Fırın",false))
        listData.add(isletmeler(3,"reno","tomorrow","2.5 km","25","5 left","Fırın",false))
        listData.add(isletmeler(4,"BferrerMW","tomorrow","2.5 km","25","5 left","Fırın",false))
    }

    companion object{
        @JvmStatic
        fun newInstance() =
            discover_fragment().apply {
                arguments=Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    private fun favori_isletmeler(){


        val url="http://192.168.43.114/restoranapp/tum_isletmeler.php"

        val istek : StringRequest = object : StringRequest(Method.GET,url, Response.Listener { cevap ->

            // Log.e("silmecevap2",cevap)

            try {

                val jsonObject= JSONObject(cevap)
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
                        listData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur"),true))

                    }

                    initRecyclerView()


                }

            }catch (e: JSONException){

            }



        }, Response.ErrorListener { e -> e.printStackTrace() }){}

        Volley.newRequestQueue(context).add(istek)
    }


    override fun onItemClick(dataModel: isletmeler) {
       //val intent = Intent (getActivity(), restoran_sayfa::class.java)
        //intent.putExtra("urun_id",dataModel.urun_id.toString())
        //getActivity()?.startActivity(intent)
    }

    override fun onfvbuttonclick(fvModel: isletmeler) {
        if(fvModel.isFavorite==true){
            listData.remove(fvModel)

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