package com.example.restoranapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.RecyclerViewAdapter

class browse_fragment : Fragment(), RecyclerViewAdapter.ClickListener {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var adapter2: RecyclerViewAdapter
    val listData: ArrayList<urunler> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.browse_fragment,container,false)
        buildDisplayData()
        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view: View) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.browse_recylview)

        recyclerView.layoutManager=LinearLayoutManager(activity)

        (recyclerView.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.VERTICAL

        adapter =  RecyclerViewAdapter(listData,this)

        recyclerView.adapter = adapter

    }

    private fun buildDisplayData() {
        listData.add(urunler(1,"salih","tomorrow","2.5 km","25","5 left"))
        listData.add(urunler(2,"ramazan","tomorrow","2.5 km","25","5 left"))
        listData.add(urunler(3,"yasin","tomorrow","2.5 km","25","5 left"))
        listData.add(urunler(4,"imad","tomorrow","2.5 km","25","5 left"))
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
        TODO("Not yet implemented")
    }



}