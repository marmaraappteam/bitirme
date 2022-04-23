package com.example.restoranapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_sepetim.*

class sepetim : AppCompatActivity() , sepetim_recyviewadapter.ClickListener {
    private lateinit var adapter: sepetim_recyviewadapter
    private lateinit var adapter2: sepetim_recyviewadapter
    val listData: ArrayList<urunler> = ArrayList()
    val templistData: ArrayList<urunler> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sepetim)

        //buildDisplayData()
        templistData.addAll(listData)
        initRecyclerView(sepet_urunrv.rootView)

    }

    private fun initRecyclerView(view: View) {
        val recyclerView=view.findViewById<RecyclerView>(R.id.sepet_urunrv)

        recyclerView.layoutManager= LinearLayoutManager(this)

        (recyclerView.layoutManager as LinearLayoutManager).orientation= LinearLayoutManager.VERTICAL

        adapter =  sepetim_recyviewadapter(templistData,this)

        recyclerView.adapter = adapter

    }

    /*private fun buildDisplayData() {
        listData.add(urunler(1,"Çınar Pide Fırın","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(2,"Simit Sarayı","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(3,"Vedat Usta","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(4,"Waffle","Yarın","2.5 km","25","Son 5 Ürün"))
        listData.add(urunler(4,"Waffle","Yarın","2.5 km","25","Son 5 Ürün"))


    }*/




    override fun onItemClick(dataModel: urunler) {
      //  val transaction = activity?.supportFragmentManager?.beginTransaction()
       // transaction?.replace(R.id.fragment_tutucu, restoran_sayfa_fragment())
        //transaction?.disallowAddToBackStack()
        //transaction?.commit()

        //val intent = Intent (getActivity(), restoran_sayfa::class.java)
        //intent.putExtra("urun_id",dataModel.urun_id.toString())
        //getActivity()?.startActivity(intent)
    }
}