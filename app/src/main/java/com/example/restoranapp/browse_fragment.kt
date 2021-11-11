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
    val listData: ArrayList<ulkeler> = ArrayList()


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
        listData.add(ulkeler(1,"salih"))
        listData.add(ulkeler(2,"ramazan"))
        listData.add(ulkeler(3,"yasin"))
        listData.add(ulkeler(4,"imad"))
    }

    companion object{
        @JvmStatic
        fun newInstance() =
            discover_fragment().apply {
                arguments=Bundle().apply {

                }
            }
    }



    override fun onItemClick(dataModel: ulkeler) {
        TODO("Not yet implemented")
    }

}