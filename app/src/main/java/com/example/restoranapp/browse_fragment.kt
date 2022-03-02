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

class browse_fragment : Fragment(), RecyclerViewAdapter.ClickListener {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var adapter2: RecyclerViewAdapter
    val listData: ArrayList<urunler> = ArrayList()
    val templistData: ArrayList<urunler> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.browse_fragment,container,false)
        buildDisplayData()
        templistData.addAll(listData)
        initRecyclerView(view)



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
                    (it.restoran_ad?.contains(p0!!)!!)

                }
                    .forEach { templistData.add(it) }
                initRecyclerView(view)

                return false
            }

        })

    }

    private fun initRecyclerView(view: View) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.browse_recylview)

        recyclerView.layoutManager=LinearLayoutManager(activity)

        (recyclerView.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.VERTICAL

        adapter =  RecyclerViewAdapter(templistData,this)

        recyclerView.adapter = adapter

    }

    private fun buildDisplayData() {
        listData.add(urunler(1,"Çınar Pide Fırın","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(2,"Simit Sarayı","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(3,"Vedat Usta","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(4,"Waffle","Yarın","2.5 km","25","Son 5 Ürün"))


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
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_tutucu, restoran_sayfa_fragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()

    //val intent = Intent (getActivity(), restoran_sayfa::class.java)
        //intent.putExtra("urun_id",dataModel.urun_id.toString())
        //getActivity()?.startActivity(intent)
    }



}