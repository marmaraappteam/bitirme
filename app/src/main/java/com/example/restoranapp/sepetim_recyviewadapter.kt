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

class sepetim_recyviewadapter(val listData: List<urunler>, val clickListener: ClickListener): RecyclerView.Adapter<sepetim_recyviewadapter.MyViewHolder>() {
    var templistData: ArrayList<urunler> = ArrayList()
    var templistData2: ArrayList<urunler> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sepetim_recyviewadapter.MyViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.card_sepetim,parent,false)
        templistData.addAll(listData)
        templistData2.add(urunler(1,"asd","tomorrow","2.5 km","25","5 left"))
        templistData2.add(urunler(2,"ramasdazan","tomorrow","2.5 km","25","5 left"))
        templistData2.add(urunler(3,"asd","tomorrow","2.5 km","25","5 left"))
        templistData2.add(urunler(4,"asd","tomorrow","2.5 km","25","5 left"))
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var urun_ad : TextView
        var urun_fiyat:TextView
        //  var mesafe_tv:TextView
        var cardview: CardView
        var urun_sayi: TextView

        //var fvbutton : ImageButton


        init {
            urun_ad=view.findViewById(R.id.sepet_urunad)
            urun_fiyat=view.findViewById(R.id.sepet_urunfiyat)
            //    mesafe_tv=view.findViewById(R.id.restmesafe)
            urun_sayi=view.findViewById(R.id.sepet_urunsayi)

            cardview=view.findViewById(R.id.sepet_satir)
            //   fvbutton=view.findViewById(R.id.favori_button)
        }


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.urun_ad.text = listData.get(position).restoran_ad
        holder.urun_fiyat.text = listData.get(position).teslim_tarihi
        //  holder.mesafe_tv.text = listData.get(position).mesafe
        holder.urun_sayi.text = listData.get(position).urun_fiyat

        holder.itemView.setOnClickListener{
            clickListener.onItemClick(listData.get(position))
        }



    }
    interface ClickListener {
        fun onItemClick(dataModel: urunler)


    }


}