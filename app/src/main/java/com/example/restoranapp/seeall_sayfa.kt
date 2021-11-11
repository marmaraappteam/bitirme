package com.example.restoranapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_seeall_sayfa.*

class seeall_sayfa : AppCompatActivity(), RecyclerViewAdapter.ClickListener {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var adapter2: RecyclerViewAdapter

    val listData: ArrayList<ulkeler> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seeall_sayfa)

        buildDisplayData()
        initRecyclerView(this)


    }

    private fun initRecyclerView(view: seeall_sayfa) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_seeall)

        recyclerView.layoutManager= LinearLayoutManager(this)

        (recyclerView.layoutManager as LinearLayoutManager).orientation= LinearLayoutManager.VERTICAL

        adapter =  RecyclerViewAdapter(listData,this)

        recyclerView.adapter = adapter

    }

    private fun buildDisplayData() {
        listData.add(ulkeler(1,"BMW"))
        listData.add(ulkeler(2,"veyt"))
        listData.add(ulkeler(3,"reno"))
        listData.add(ulkeler(4,"BferrerMW"))
    }

    override fun onItemClick(dataModel: ulkeler) {
        TODO("Not yet implemented")
    }
}