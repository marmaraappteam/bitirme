package com.example.restoranapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tempFragment:Fragment

    private lateinit var ulkelerliste:ArrayList<ulkeler>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction().add(R.id.fragment_tutucu,discover_fragment()).commit()

        bottom_navigation.setOnNavigationItemSelectedListener {menuItem->
            if (menuItem.itemId==R.id.bottom_discover)
            {
                Toast.makeText(applicationContext,"birinci",Toast.LENGTH_SHORT).show()
                tempFragment=discover_fragment()
            }
            if (menuItem.itemId==R.id.bottom_browse)
            {
                Toast.makeText(applicationContext,"ikinci",Toast.LENGTH_SHORT).show()
                tempFragment=browse_fragment()
            }
            if (menuItem.itemId==R.id.bottom_more)
            {
                Toast.makeText(applicationContext,"ucuncu",Toast.LENGTH_SHORT).show()
                tempFragment=more_fragment()
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_tutucu,tempFragment).commit()

            true

        }
    }

}