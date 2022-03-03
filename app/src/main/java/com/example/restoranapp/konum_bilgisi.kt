package com.example.restoranapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class konum_bilgisi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konum_bilgisi)

        val data = arrayOf("Java", "Python", "C++", "C#", "Angular", "Go")
        val data_mesafe = arrayOf("10 Km", "20 Km", "30 Km", "40 Km", "50 Km", "60 Km")

        val adapter = ArrayAdapter(this, R.layout.spinner_item_selected, data)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        val adapter2 = ArrayAdapter(this, R.layout.spinner_item_selected, data_mesafe)
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item)



        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(applicationContext, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        spinner2.adapter = adapter
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(applicationContext, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        val spinner3 = findViewById<Spinner>(R.id.spinner3)
        spinner3.adapter = adapter
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(applicationContext, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        val spinner4 = findViewById<Spinner>(R.id.spinner4)
        spinner4.adapter = adapter2
        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(applicationContext, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }
}