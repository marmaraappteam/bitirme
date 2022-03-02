package com.example.restoranapp


import android.content.Intent
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

import com.example.myapplication.RecyclerViewAdapter
import kotlinx.android.synthetic.main.browse_fragment.*
import kotlinx.android.synthetic.main.discover_fragment.*
import kotlinx.android.synthetic.main.restoran_card.*
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
          buildDisplayData()
         templistData.addAll(listData)
        initRecyclerView(view)



        return view
    }



    private fun initRecyclerView(view: View) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.urun_recv)

        recyclerView.layoutManager=LinearLayoutManager(activity)

        (recyclerView.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.VERTICAL

        adapter =  urun_recyview(templistData,this)

        recyclerView.adapter = adapter

    }

    private fun buildDisplayData() {
        listData.add(urunler(1,"Ballım Pasta","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(2,"Magnolya","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(3,"Dondurma","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(4,"Çerez","Yarın","2.5 km","25","Son 5 Ürün"))


    }

    companion object{
        @JvmStatic
        fun newInstance() =
            discover_fragment().apply {
                arguments=Bundle().apply {

                }
            }
    }



    override fun onItemClick(dataModel: urunler) {
      //  val intent = Intent (getActivity(), restoran_sayfa::class.java)
       // intent.putExtra("urun_id",dataModel.urun_id.toString())
        //getActivity()?.startActivity(intent)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_tutucu, urun_sayfasi())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }



}