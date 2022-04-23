package com.example.restoranapp


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton

import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.example.myapplication.RecyclerViewAdapter
import kotlinx.android.synthetic.main.discover_fragment.*
import kotlinx.android.synthetic.main.restoran_card.*
import org.json.JSONException
import org.json.JSONObject


class discover_fragment : Fragment(), RecyclerViewAdapter.ClickListener , View.OnClickListener {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var adapter2: RecyclerViewAdapter

    val listData: ArrayList<isletmeler> = ArrayList()
    val templistData: ArrayList<isletmeler> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.discover_fragment,container,false)

        val btn: TextView = view.findViewById(R.id.seeallbutton1)
        btn.setOnClickListener(this)
        val btn2: TextView = view.findViewById(R.id.seeallbutton2)
        btn2.setOnClickListener(this)
        val change_btn: AppCompatImageButton = view.findViewById(R.id.changebuton)
        btn2.setOnClickListener(this)

        //buildDisplayData()
        isletme_cek(view)
        urun_cek_fırın(view)
        initRecyclerView(view)



        return view
    }

    private fun initRecyclerView(view: View) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(activity)
        (recyclerView.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.HORIZONTAL
        adapter =  RecyclerViewAdapter(listData,this)
        recyclerView.adapter = adapter


        val recyclerView2=view.findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView2.layoutManager=LinearLayoutManager(activity)
        (recyclerView2.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.HORIZONTAL
        adapter2 =  RecyclerViewAdapter(templistData,this)
        recyclerView2.adapter = adapter2
    }

    private fun urun_cek_fırın(view: View){
        val url="http://192.168.43.114/restoranapp/tum_isletmeler.php"

        val istek : StringRequest = object : StringRequest(Method.GET,url, Response.Listener { cevap ->


            try {

                val jsonObject= JSONObject(cevap)
                val isletmelerListe=jsonObject.getJSONArray("isletmeler")



                for (i in 0 until isletmelerListe.length()){
                    val k=isletmelerListe.getJSONObject(i)
                    if (k.getString("isletme_tur")=="Fırın"){

                        templistData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur")))

                    }

                    initRecyclerView(view)

                }

            }catch (e: JSONException){

            }



        }, Response.ErrorListener { e -> e.printStackTrace() }){}

        Volley.newRequestQueue(context).add(istek)
    }

    private fun isletme_cek(view: View){
        val url="http://192.168.43.114/restoranapp/tum_isletmeler.php"

        val istek : StringRequest = object : StringRequest(Method.GET,url, Response.Listener { cevap ->

            // Log.e("silmecevap2",cevap)

            try {

                val jsonObject= JSONObject(cevap)
                val isletmelerListe=jsonObject.getJSONArray("isletmeler")



                for (i in 0 until isletmelerListe.length()){
                    val k=isletmelerListe.getJSONObject(i)
                    listData.add(isletmeler(k.getInt("isletme_id"),k.getString("isletme_ad"),k.getString("isletme_konum"),k.getString("isletme_asaat"),k.getString("isletme_ksaat"),k.getString("isletme_point"),k.getString("isletme_tur")))

                    initRecyclerView(view)

                }

            }catch (e: JSONException){

            }



        }, Response.ErrorListener { e -> e.printStackTrace() }){}

        Volley.newRequestQueue(context).add(istek)
    }

/*    private fun buildDisplayData() {
        listData.add(isletmeler(1,"BMW","tomorrow","2.5 km","25","5 left","fırın"))
        listData.add(isletmeler(2,"veyt","tomorrow","2.5 km","25","5 left","fırın"))
        listData.add(isletmeler(3,"reno","tomorrow","2.5 km","25","5 left","fırın"))
        listData.add(isletmeler(4,"BferrerMW","tomorrow","2.5 km","25","5 left","fırın"))
    }*/

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




        changebuton.setOnClickListener{

           val intent = Intent (getActivity(), konum_bilgisi::class.java)
            getActivity()?.startActivity(intent)
           // changebuton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }




    }


    override fun onItemClick(dataModel: isletmeler) {
        val bundle = Bundle()
        val frag=restoran_sayfa_fragment()

        bundle.putString("isletme_id", dataModel.isletme_id.toString())
        frag.arguments=bundle
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_tutucu, frag)
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }



    override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.seeallbutton1 -> {
                    val intent = Intent (getActivity(), seeall_sayfa::class.java)
                    intent.putExtra("title",baslik1.text)
                    getActivity()?.startActivity(intent)
                }
            }
        when (p0?.id) {
            R.id.seeallbutton2 -> {
                val intent = Intent (getActivity(), seeall_sayfa::class.java)
                intent.putExtra("title",baslik2.text)
                getActivity()?.startActivity(intent)
            }
        }


    }



}