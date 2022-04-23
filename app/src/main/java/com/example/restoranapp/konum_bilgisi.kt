package com.example.restoranapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_konum_bilgisi.*

class konum_bilgisi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konum_bilgisi)

        val data = arrayOf("İstanbul", "Niğde", "Ankara", "Kayseri", "Erzincan", "Malatya")
        val data_mesafe = arrayOf("10 Km", "20 Km", "30 Km", "40 Km", "50 Km", "60 Km")

        val adapter = ArrayAdapter(this, R.layout.spinner_item_selected, data)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        val adapter2 = ArrayAdapter(this, R.layout.spinner_item_selected, data_mesafe)
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item)



        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
             //   Toast.makeText(applicationContext, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        spinner2.adapter = adapter
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
             //   Toast.makeText(applicationContext, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        val spinner3 = findViewById<Spinner>(R.id.spinner3)
        spinner3.adapter = adapter
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
              // Toast.makeText(applicationContext, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        val spinner4 = findViewById<Spinner>(R.id.spinner4)
        spinner4.adapter = adapter2
        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
               // Toast.makeText(applicationContext, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                if (radioButton.id==checkedId){

                    val gmmIntentUri = Uri.parse("geo:37.7749,-122.4192?q=" + Uri.encode("1st & Pike, Seattle"))
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    mapIntent.resolveActivity(packageManager)?.let {
                        startActivity(mapIntent)
                    }


                       textView24.visibility=View.INVISIBLE
                    textView25.visibility=View.INVISIBLE
                    textView26.visibility=View.INVISIBLE
                    textView27.visibility=View.INVISIBLE
                    spinner.visibility=View.INVISIBLE
                    spinner2.visibility=View.INVISIBLE
                    spinner3.visibility=View.INVISIBLE
                    spinner4.visibility=View.INVISIBLE
                }
                else if(radioButton2.id==checkedId){
                    textView24.visibility=View.VISIBLE
                    textView25.visibility=View.VISIBLE
                    textView26.visibility=View.VISIBLE
                    textView27.visibility=View.VISIBLE
                    spinner.visibility=View.VISIBLE
                    spinner2.visibility=View.VISIBLE
                    spinner3.visibility=View.VISIBLE
                    spinner4.visibility=View.VISIBLE
                }

            })

    }


}