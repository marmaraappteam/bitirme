package com.example.restoranapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_restoran_sayfa.*
import android.view.ViewGroup

import androidx.constraintlayout.widget.ConstraintLayout




class restoran_sayfa : AppCompatActivity() {

    lateinit var baslik:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restoran_sayfa)
        supportActionBar?.hide()
        baslik= intent?.getStringExtra("urun_id").toString()
        textView5.setText(baslik)
        constraintLayout8.setOnClickListener{
            val params = deneme.layoutParams
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            deneme.layoutParams = params
        }


    }
}