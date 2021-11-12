package com.example.restoranapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class restoran_sayfa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restoran_sayfa)
        supportActionBar?.hide()
    }
}