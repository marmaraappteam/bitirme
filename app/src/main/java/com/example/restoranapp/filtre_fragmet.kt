package com.example.restoranapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.filtre_fragmet.*


class filtre_fragmet : Fragment() {


    var listDataforPuan: ArrayList<String> = ArrayList()
    var listDataforTur: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.filtre_fragmet,container,false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        if (this.arguments?.getStringArrayList("filtre_type")!=null){
            listDataforPuan= this.arguments?.getStringArrayList("filtre_type") as ArrayList<String>
            if (listDataforPuan.contains("Changed")){
                for (i in listDataforPuan){
                    if (i=="1"){ point_1.isChecked=true }
                    if (i=="2"){ point_2.isChecked=true }
                    if (i=="3"){ point_3.isChecked=true }
                    if (i=="4"){ point_4.isChecked=true }
                    if (i=="5"){ point_5.isChecked=true }

                }
            }
            else{
                point_1.isChecked=true
                point_2.isChecked=true
                point_3.isChecked=true
                point_4.isChecked=true
                point_5.isChecked=true
            }


        }

        if (this.arguments?.getStringArrayList("filtre_tur")!=null){
            listDataforTur= this.arguments?.getStringArrayList("filtre_tur") as ArrayList<String>

            if (listDataforTur.contains("Changed")){
                for (i in listDataforTur){
                    if (i=="Hepsi"){ btn_fhepsi.isChecked=true }
                    if (i=="Restoran"){ btn_frestoran.isChecked=true }
                    if (i=="Market"){ btn_fmarket.isChecked=true }
                    if (i=="Kafe"){ btn_fkafe.isChecked=true }
                    if (i=="Fırın"){ btn_ffirin.isChecked=true }
                    if (i=="Tatlı"){ btn_ftatli.isChecked=true }
                }
            }
            else{
                btn_fhepsi.isChecked=true
                btn_frestoran.isChecked=true
                btn_fmarket.isChecked=true
                btn_fkafe.isChecked=true
                btn_ffirin.isChecked=true
                btn_ftatli.isChecked=true
            }


        }
        btn_fhepsi.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforTur.add(btn_fhepsi.text.toString())
                btn_frestoran.isChecked=true
                btn_fmarket.isChecked=true
                btn_fkafe.isChecked=true
                btn_ffirin.isChecked=true
                btn_ftatli.isChecked=true
            }
            else{
                listDataforTur.remove(btn_fhepsi.text.toString())
                btn_frestoran.isChecked=false
                btn_fmarket.isChecked=false
                btn_fkafe.isChecked=false
                btn_ffirin.isChecked=false
                btn_ftatli.isChecked=false

            }

        }
        btn_frestoran.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforTur.add(btn_frestoran.text.toString())
            }
            else{
                listDataforTur.remove(btn_frestoran.text.toString())
                btn_fhepsi.isChecked=false

            }

        }
        btn_fkafe.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforTur.add(btn_fkafe.text.toString())
            }
            else{
                listDataforTur.remove(btn_fkafe.text.toString())
                btn_fhepsi.isChecked=false
            }

        }
        btn_ffirin.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforTur.add(btn_ffirin.text.toString())
            }
            else{
                listDataforTur.remove(btn_ffirin.text.toString())
                btn_fhepsi.isChecked=false
            }

        }
        btn_fmarket.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforTur.add(btn_fmarket.text.toString())
            }
            else{
                listDataforTur.remove(btn_fmarket.text.toString())
                btn_fhepsi.isChecked=false
            }

        }
        btn_ftatli.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforTur.add(btn_ftatli.text.toString())
            }
            else{
                listDataforTur.remove(btn_ftatli.text.toString())
                btn_fhepsi.isChecked=false
            }

        }




        point_1.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforPuan.add(point_1.text.toString())
            }
            else{
                listDataforPuan.remove(point_1.text.toString())
            }

        }
        point_2.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforPuan.add(point_2.text.toString())
            }
            else{
                listDataforPuan.remove(point_2.text.toString())
            }

        }
        point_3.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforPuan.add(point_3.text.toString())
            }
            else{
                listDataforPuan.remove(point_3.text.toString())
            }

        }
        point_4.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforPuan.add(point_4.text.toString())
            }
            else{
                listDataforPuan.remove(point_4.text.toString())
            }

        }
        point_5.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                listDataforPuan.add(point_5.text.toString())
            }
            else{
                listDataforPuan.remove(point_5.text.toString())
            }

        }

        if (!listDataforTur.contains("Changed")){
            listDataforPuan.add("1") ; listDataforPuan.add("2") ;listDataforPuan.add("3")
            listDataforPuan.add("4") ; listDataforPuan.add("5") ;listDataforTur.add("Hepsi")
            listDataforTur.add("Restoran") ; listDataforTur.add("Market") ;listDataforTur.add("Kafe")
            listDataforTur.add("Fırın") ; listDataforTur.add("Tatlı")
        }

        btn_filterapply.setOnClickListener {
            val b = Bundle()
            val frag=browse_fragment()
            listDataforTur.add("Changed")
            listDataforPuan.add("Changed")
            b.putStringArrayList("filtre_type", listDataforPuan)
            b.putStringArrayList("filtre_tur", listDataforTur)
            frag.arguments=b
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_tutucu, frag)
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }
        btn_fsifirla.setOnClickListener{
            btn_fhepsi.isChecked=true ;btn_frestoran.isChecked=true ;btn_fmarket.isChecked=true
            btn_fkafe.isChecked=true ;btn_ffirin.isChecked=true ;btn_ftatli.isChecked=true
            point_1.isChecked=true ;point_2.isChecked=true ;point_3.isChecked=true
            point_4.isChecked=true ;point_5.isChecked=true
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            filtre_fragmet().apply {
                arguments = Bundle().apply {

                }
            }
    }
}