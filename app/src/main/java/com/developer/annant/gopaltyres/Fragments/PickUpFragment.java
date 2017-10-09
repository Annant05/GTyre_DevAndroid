package com.developer.annant.gopaltyres.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.developer.annant.gopaltyres.R;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickUpFragment extends Fragment {


    public PickUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list_maker, container, false);

        final ArrayList<TyreDataVariable> tyreDataVariable = new ArrayList<>();

        //Data feed Here To create ArratList

        tyreDataVariable.add(new TyreDataVariable("Super Lug 99", "7.00-15","Na" ));


        /*

        Feed Data here In ArrayList <>;


        */

        TyreDataAdapter adapter = new TyreDataAdapter(getActivity(), tyreDataVariable);

        ListView listView = (ListView) rootView.findViewById(R.id.common_listview_layout);
        listView.setAdapter(adapter);



        return rootView;

    }

}
