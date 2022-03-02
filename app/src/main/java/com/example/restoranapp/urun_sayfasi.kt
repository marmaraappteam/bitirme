package com.example.restoranapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_urun_sayfasi.*


private const val ARG_PARAM2 = "param2"

class urun_sayfasi : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_urun_sayfasi, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        constraintLayout8.setOnClickListener{
            val params = deneme.layoutParams
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            deneme.layoutParams = params
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            urun_sayfasi().apply {
                arguments = Bundle().apply {

                }
            }
    }
}