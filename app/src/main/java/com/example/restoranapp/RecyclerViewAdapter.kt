package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.restoranapp.R
import com.example.restoranapp.ulkeler

class RecyclerViewAdapter(val listData: List<ulkeler>,val clickListener: ClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.restoran_card,parent,false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var titleTextView : TextView
        var cardview: CardView

        init {
            titleTextView=view.findViewById(R.id.restoran_ad)
            cardview=view.findViewById(R.id.restorancard_satir)
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = listData.get(position).ulkead
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(listData.get(position))
        }
    }
    interface ClickListener {
        fun onItemClick(dataModel: ulkeler)

    }
}