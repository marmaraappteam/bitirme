package com.example.restoranapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast

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
import kotlinx.android.synthetic.main.fragment_restoran_sayfa_fragment.*
import kotlinx.android.synthetic.main.restoran_card.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class restoran_sayfa_fragment : Fragment(), urun_recyview.ClickListener {

    private lateinit var adapter: urun_recyview
    private lateinit var adapter2: urun_recyview
    val listData: ArrayList<urunler> = ArrayList()
    val templistData: ArrayList<urunler> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_restoran_sayfa_fragment,container,false)
        isletme_bilgileri_cek()
        urun_cek(view)
        // buildDisplayData()
        // templistData.addAll(listData)
       // initRecyclerView(view)
        Toast.makeText(context, arguments?.getString("isletme_id"),Toast.LENGTH_SHORT).show()



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        constraintLayout4.setOnClickListener{
            val gmmIntentUri = Uri.parse("geo:37.7749,-122.4192?q=" + Uri.encode("1st & Pike, Seattle"))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
           // mapIntent.resolveActivity(packageManager)?.let {
                startActivity(mapIntent)
            //}
        }
    }



    private fun initRecyclerView(view: View) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.urun_recv)

        recyclerView.layoutManager=LinearLayoutManager(activity)

        (recyclerView.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.VERTICAL

        adapter =  urun_recyview(templistData,this)

        recyclerView.adapter = adapter

    }
    private fun urun_cek(view: View){
        val url="http://192.168.43.114/restoranapp/tum_urunler.php"

        val istek : StringRequest = object : StringRequest(Method.GET,url, Response.Listener { cevap ->

            // Log.e("silmecevap2",cevap)

            try {

                val jsonObject= JSONObject(cevap)
                val productListe=jsonObject.getJSONArray("products")



                for (i in 0 until productListe.length()){
                    val k=productListe.getJSONObject(i)
                    if (k.getInt("isletme_id").toString()== arguments?.getString("isletme_id")){
                        templistData.add(urunler(k.getInt("product_id"),k.getInt("isletme_id"),k.getString("product_name"),k.getString("product_desc"),k.getString("product_price"),k.getString("product_amount")))

                    }

                    initRecyclerView(view)

                }

            }catch (e: JSONException){

            }



        }, Response.ErrorListener { e -> e.printStackTrace() }){}

        Volley.newRequestQueue(context).add(istek)
    }

    private fun isletme_bilgileri_cek(){
        val url="http://192.168.43.114/restoranapp/tum_isletmeler.php"

        val istek : StringRequest = object : StringRequest(Method.GET,url, Response.Listener { cevap ->

            // Log.e("silmecevap2",cevap)

            try {

                val jsonObject= JSONObject(cevap)
                val productListe=jsonObject.getJSONArray("isletmeler")



                for (i in 0 until productListe.length()){
                    val k=productListe.getJSONObject(i)
                    if (k.getInt("isletme_id").toString()== arguments?.getString("isletme_id")){
                    isletme_name.setText(k.getString("isletme_ad"))
                    isletme_point.setText(k.getString("isletme_point"))
                    }


                }

            }catch (e: JSONException){

            }



        }, Response.ErrorListener { e -> e.printStackTrace() }){}

        Volley.newRequestQueue(context).add(istek)
    }

    /*private fun buildDisplayData() {
        listData.add(urunler(1,"Ballım Pasta","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(2,"Magnolya","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(3,"Dondurma","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(4,"Çerez","Yarın","2.5 km","25","Son 5 Ürün"))


    }*/

    companion object{
        @JvmStatic
        fun newInstance() =
            discover_fragment().apply {
                arguments=Bundle().apply {

                }
            }
    }



    override fun onItemClick(dataModel: urunler) {
        val bundle = Bundle()
        val frag=urun_sayfasi()

        bundle.putString("urun_id", dataModel.product_id.toString())
        frag.arguments=bundle
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_tutucu, frag)
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }



}