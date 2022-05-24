package com.example.myapplication

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
import com.example.restoranapp.isletmeler
import com.example.restoranapp.ulkeler
import com.example.restoranapp.urunler
import org.w3c.dom.Text

class RecyclerViewAdapter(val listData: List<isletmeler>, val clickListener: ClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
   // var templistData: ArrayList<isletmeler> = ArrayList()
    //var templistData2: ArrayList<isletmeler> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.restoran_card,parent,false)
     //   templistData.addAll(listData)
      //  templistData2.add(isletmeler(1,"asd","tomorrow","2.5 km","25","5 left","Fırın"))
       // templistData2.add(isletmeler(2,"ramasdazan","tomorrow","2.5 km","25","5 left","Fırın"))
       // templistData2.add(isletmeler(3,"asd","tomorrow","2.5 km","25","5 left","Fırın"))
       // templistData2.add(isletmeler(4,"asd","tomorrow","2.5 km","25","5 left","Fırın"))
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

        var urun_kalan_tv: TextView
        var fvbutton : ImageButton


        init {
            titleTextView=view.findViewById(R.id.restoran_ad)
            siparistarih_tv=view.findViewById(R.id.restsiparistarih)
            mesafe_tv=view.findViewById(R.id.restmesafe)

            urun_kalan_tv=view.findViewById(R.id.card_kalan)
            cardview=view.findViewById(R.id.restorancard_satir)
            fvbutton=view.findViewById(R.id.favori_button)
        }


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = listData.get(position).isletme_ad
        holder.siparistarih_tv.text = listData.get(position).isletme_asaat
        holder.mesafe_tv.text = listData.get(position).isletme_konum
        holder.urun_kalan_tv.text = listData.get(position).isletme_point
        if (listData.get(position).isFavorite==true){
            holder.fvbutton.setImageResource(R.drawable.ic_baseline_favorite_24)
            holder.fvbutton.setTag("favorite")
        }
        if (listData.get(position).isFavorite==false){
            holder.fvbutton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            holder.fvbutton.setTag("unfavorite")
        }
        holder.fvbutton.setOnClickListener{

            clickListener.onfvbuttonclick(listData.get(position))
        }
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(listData.get(position))
        }

        if (holder.titleTextView.text=="reno"){
            holder.fvbutton.setTag("favorite")
            holder.fvbutton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        holder.fvbutton.setTag("unfavorite")
        /*holder.fvbutton.setOnClickListener{
            if (holder.fvbutton.getTag()=="unfavorite"){
                holder.fvbutton.setImageResource(R.drawable.ic_baseline_favorite_24)
                holder.fvbutton.setTag("favorite")




            }
            else if(holder.fvbutton.getTag()=="favorite"){
                holder.fvbutton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                holder.fvbutton.setTag("unfavorite")

            }

          //  clickListener.onItemClickfavorite(listData.get(position))
        }*/

    }
    interface ClickListener {
        fun onItemClick(dataModel: isletmeler)
        fun onfvbuttonclick(fvModel: isletmeler)

    }



}