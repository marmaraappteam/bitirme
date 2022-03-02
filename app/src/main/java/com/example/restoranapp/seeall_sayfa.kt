package com.example.restoranapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.RecyclerViewAdapter
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.activity_seeall_sayfa.*

class seeall_sayfa : AppCompatActivity(), RecyclerViewAdapter.ClickListener {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var adapter2: RecyclerViewAdapter

    val listData: ArrayList<urunler> = ArrayList()

    lateinit var baslik:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seeall_sayfa)
        supportActionBar?.hide()
        buildDisplayData()
        initRecyclerView(this)
        baslik= intent?.getStringExtra("title").toString()
        baslik_seeall.setText(baslik.toString())

    }

    private fun initRecyclerView(view: seeall_sayfa) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_seeall)

        recyclerView.layoutManager= LinearLayoutManager(this)

        (recyclerView.layoutManager as LinearLayoutManager).orientation= LinearLayoutManager.VERTICAL

        adapter =  RecyclerViewAdapter(listData,this)

        recyclerView.adapter = adapter

    }

    private fun buildDisplayData() {
        listData.add(urunler(1,"BMW","tomorrow","2.5 km","25","5 left"))
        listData.add(urunler(2,"veyt","tomorrow","2.5 km","25","5 left"))
        listData.add(urunler(3,"reno","tomorrow","2.5 km","25","5 left"))
        listData.add(urunler(4,"BferrerMW","tomorrow","2.5 km","25","5 left"))

    }

    override fun onItemClick(dataModel: urunler) {
        //val intent = Intent (this, restoran_sayfa::class.java)
        //this?.startActivity(intent)
    }


}