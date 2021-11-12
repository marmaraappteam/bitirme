package com.example.restoranapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.RecyclerViewAdapter
import kotlinx.android.synthetic.main.discover_fragment.*
import androidx.appcompat.app.AppCompatActivity




class discover_fragment : Fragment(), RecyclerViewAdapter.ClickListener , View.OnClickListener {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var adapter2: RecyclerViewAdapter

    val listData: ArrayList<ulkeler> = ArrayList()


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
       // (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        buildDisplayData()
        initRecyclerView(view)



        return view
    }

    private fun initRecyclerView(view: View) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerView2=view.findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView.layoutManager=LinearLayoutManager(activity)
        recyclerView2.layoutManager=LinearLayoutManager(activity)
        (recyclerView.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.HORIZONTAL
        (recyclerView2.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.HORIZONTAL
        adapter =  RecyclerViewAdapter(listData,this)
        adapter2 =  RecyclerViewAdapter(listData,this)
        recyclerView.adapter = adapter
        recyclerView2.adapter = adapter2
    }

    private fun buildDisplayData() {
        listData.add(ulkeler(1,"BMW"))
        listData.add(ulkeler(2,"veyt"))
        listData.add(ulkeler(3,"reno"))
        listData.add(ulkeler(4,"BferrerMW"))
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




        changebuton.setOnClickListener{
            val intent = Intent (getActivity(), restoran_sayfa::class.java)
            getActivity()?.startActivity(intent)
        }




    }


    override fun onItemClick(dataModel: ulkeler) {
        val intent = Intent (getActivity(), restoran_sayfa::class.java)
        getActivity()?.startActivity(intent)
    }

    override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.seeallbutton1 -> {
                    val intent = Intent (getActivity(), seeall_sayfa::class.java)
                    intent.putExtra("title",baslik1.text)
                    getActivity()?.startActivity(intent)
                }
       /* else -> {
        }*/
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