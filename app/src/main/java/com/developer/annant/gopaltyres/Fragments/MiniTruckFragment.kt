package com.developer.annant.gopaltyres.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.developer.annant.gopaltyres.Extras_imp.TyreDataAdapter
import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable
import com.developer.annant.gopaltyres.R
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class MiniTruckFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater!!.inflate(R.layout.activity_list_maker, container, false)

        val tyreDataVariable = ArrayList<TyreDataVariable>()

        //Data feed Here To create ArrayList
        //
        tyreDataVariable.add(TyreDataVariable("Savari", "155D12 ", "2500"))
        tyreDataVariable.add(TyreDataVariable("Savari", "165D12 ", "2800"))
        tyreDataVariable.add(TyreDataVariable("Savari", "165D13 ", "3150"))
        tyreDataVariable.add(TyreDataVariable("Savari", "165D14 ", "3300"))


        //
        tyreDataVariable.add(TyreDataVariable("Savari Lug", "155D12 ", "2700"))
        tyreDataVariable.add(TyreDataVariable("Savari Lug", "165D12 ", "3200"))
        tyreDataVariable.add(TyreDataVariable("Savari Lug", "165D13 ", "3350"))
        tyreDataVariable.add(TyreDataVariable("Savari Lug", "165D14 ", "3550"))


        /*
        Feed Data here In ArrayList <>;
        */

        val adapter = TyreDataAdapter(activity, tyreDataVariable)

        val listView = rootView.findViewById(R.id.common_listview_layout) as ListView
        listView.adapter = adapter

        return rootView

    }

}// Required empty public constructor
