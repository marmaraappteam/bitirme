package com.example.restoranapp

import android.annotation.SuppressLint
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.restoranapp.R
import com.example.restoranapp.ulkeler
import com.example.restoranapp.urunler
import org.w3c.dom.Text

class urun_recyview(val listData: List<urunler>, val clickListener: ClickListener): RecyclerView.Adapter<urun_recyview.MyViewHolder>() {
    var templistData: ArrayList<urunler> = ArrayList()
    var templistData2: ArrayList<urunler> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): urun_recyview.MyViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.urun_card,parent,false)
        templistData.addAll(listData)
        templistData2.add(urunler(1,1,"tomorrow","2.5 km","25","5 left"))
        templistData2.add(urunler(2,1,"tomorrow","2.5 km","25","5 left"))
        templistData2.add(urunler(3,1,"tomorrow","2.5 km","25","5 left"))
        templistData2.add(urunler(4,1,"tomorrow","2.5 km","25","5 left"))
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var titleTextView : TextView
        var siparistarih_tv:TextView
      //  var mesafe_tv:TextView
        var cardview: CardView
        var urun_fiyat_tv: TextView
        var urun_kalan_tv: TextView
        //var fvbutton : ImageButton


        init {
            titleTextView=view.findViewById(R.id.urun_ad)
            siparistarih_tv=view.findViewById(R.id.urunsiparistarih)
        //    mesafe_tv=view.findViewById(R.id.restmesafe)
            urun_fiyat_tv=view.findViewById(R.id.cardurun_fiyat)
            urun_kalan_tv=view.findViewById(R.id.uruncard_kalan)
            cardview=view.findViewById(R.id.uruncard_satir)
         //   fvbutton=view.findViewById(R.id.favori_button)
        }


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = listData.get(position).product_name
        holder.siparistarih_tv.text = listData.get(position).product_desc
      //  holder.mesafe_tv.text = listData.get(position).mesafe
        holder.urun_fiyat_tv.text = listData.get(position).product_price
        holder.urun_kalan_tv.text = listData.get(position).product_amount
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(listData.get(position))
        }



    }
    interface ClickListener {
        fun onItemClick(dataModel: urunler)


    }


}