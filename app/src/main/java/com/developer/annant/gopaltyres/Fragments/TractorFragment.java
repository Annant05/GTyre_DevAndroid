package com.developer.annant.gopaltyres.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.developer.annant.gopaltyres.Database.DataHelper;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataAdapter;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;
import com.developer.annant.gopaltyres.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TractorFragment extends Fragment {

    // Context context = getActivity();

    DataHelper mdb;

    public TractorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list_maker, container, false);

        final ArrayList<TyreDataVariable> tyreDataVariable = new ArrayList<>();

        tyreDataVariable.add(new TyreDataVariable("Shakti Life", "6.00-16","2900" ));
        mdb = new DataHelper(getActivity());
        mdb.addTyre(tyreDataVariable.get(0));

        /*
        //Data feed Here To create ArratList

        //tyreData.add(new TyreData("Shakti Life", "6.50-16 ","Rs  - 3200" ));
        tyreDataVariable.add(new TyreDataVariable("Shakti Super", "6.00-16 ","3200" ));
          //   tyreData.add(new TyreData("Shakti Super", "6.50-16 ","Rs  - 3" ));

        tyreDataVariable.add(new TyreDataVariable("Shakti Super", "12.4-28 ","28000 / 2" ));
        tyreDataVariable.add(new TyreDataVariable("Shakti Super", "13.6-28 ","34000 / 2" ));
        */

        /*
        Feed Data here In ArrayList <>;
        */

        tyreDataVariable.add(mdb.getTyre());
        TyreDataAdapter adapter = new TyreDataAdapter(getActivity(), tyreDataVariable);
        ListView listView = (ListView) rootView.findViewById(R.id.common_listview_layout);
        listView.setAdapter(adapter);

        return rootView;

    }


}
