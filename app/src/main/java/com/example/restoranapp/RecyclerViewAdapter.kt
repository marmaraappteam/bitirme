package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.restoranapp.R
import com.example.restoranapp.ulkeler
import com.example.restoranapp.urunler
import org.w3c.dom.Text

class RecyclerViewAdapter(val listData: List<urunler>, val clickListener: ClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.restoran_card,parent,false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var titleTextView : TextView
        var siparistarih_tv:TextView
        var mesafe_tv:TextView
        var cardview: CardView
        var urun_fiyat_tv: TextView
        var urun_kalan_tv: TextView

        init {
            titleTextView=view.findViewById(R.id.restoran_ad)
            siparistarih_tv=view.findViewById(R.id.restsiparistarih)
            mesafe_tv=view.findViewById(R.id.restmesafe)
            urun_fiyat_tv=view.findViewById(R.id.cardurun_fiyat)
            urun_kalan_tv=view.findViewById(R.id.card_kalan)
            cardview=view.findViewById(R.id.restorancard_satir)
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = listData.get(position).restoran_ad
        holder.siparistarih_tv.text = listData.get(position).teslim_tarihi
        holder.mesafe_tv.text = listData.get(position).mesafe
        holder.urun_fiyat_tv.text = listData.get(position).urun_fiyat
        holder.urun_kalan_tv.text = listData.get(position).kalansayisi
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(listData.get(position))
        }
    }
    interface ClickListener {
        fun onItemClick(dataModel: urunler)

    }
}