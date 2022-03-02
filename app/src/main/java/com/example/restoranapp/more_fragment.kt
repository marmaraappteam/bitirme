package com.example.restoranapp


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.RecyclerViewAdapter
import kotlinx.android.synthetic.main.discover_fragment.*

class more_fragment : Fragment(), RecyclerViewAdapter.ClickListener {

    private lateinit var adapter: RecyclerViewAdapter


    val listData: ArrayList<urunler> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.more_fragment,container,false)
        buildDisplayData()
        initRecyclerView(view)


        return view
    }

    private fun initRecyclerView(view: View) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_favorite)

        recyclerView.layoutManager=LinearLayoutManager(activity)

        (recyclerView.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.VERTICAL

        adapter =  RecyclerViewAdapter(listData,this)

        recyclerView.adapter = adapter

    }

    private fun buildDisplayData() {
        listData.add(urunler(1,"BMW","tomorrow","2.5 km","25","5 left"))
        listData.add(urunler(2,"veyt","tomorrow","2.5 km","25","5 left"))
        listData.add(urunler(3,"reno","tomorrow","2.5 km","25","5 left"))
        listData.add(urunler(4,"BferrerMW","tomorrow","2.5 km","25","5 left"))
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

    override fun onItemClick(dataModel: urunler) {
       //val intent = Intent (getActivity(), restoran_sayfa::class.java)
        //intent.putExtra("urun_id",dataModel.urun_id.toString())
        //getActivity()?.startActivity(intent)
    }



}