package com.example.restoranapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_seeall_sayfa.*

class favorite_sayfa : AppCompatActivity(), RecyclerViewAdapter.ClickListener {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var adapter2: RecyclerViewAdapter

    val listData: ArrayList<isletmeler> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_sayfa)

        buildDisplayData()
        initRecyclerView(this)


    }

    private fun initRecyclerView(view: favorite_sayfa) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_favorite)

        recyclerView.layoutManager= LinearLayoutManager(this)

        (recyclerView.layoutManager as LinearLayoutManager).orientation= LinearLayoutManager.VERTICAL

        adapter =  RecyclerViewAdapter(listData,this)

        recyclerView.adapter = adapter

    }

    private fun buildDisplayData() {
        listData.add(isletmeler(1,"BMW","tomorrow","2.5 km","25","5 left","Fırın",false))
        listData.add(isletmeler(2,"veyt","tomorrow","2.5 km","25","5 left","Fırın",false))
        listData.add(isletmeler(3,"reno","tomorrow","2.5 km","25","5 left","Fırın",false))
        listData.add(isletmeler(4,"BferrerMW","tomorrow","2.5 km","25","5 left","Fırın",false))
    }

    override fun onItemClick(dataModel: isletmeler) {
        TODO("Not yet implemented")
    }

    override fun onfvbuttonclick(fvModel: isletmeler) {
        TODO("Not yet implemented")
    }


}