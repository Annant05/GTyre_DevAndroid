package com.developer.annant.gopaltyres.Database;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.developer.annant.gopaltyres.Extras_imp.TyreDataAdapter;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;
import com.developer.annant.gopaltyres.R;

import java.util.ArrayList;

/**
 * Created by Annant on 29-12-2017.
 */

public class UpdateUi {


    private Context context;
    private ArrayList<TyreDataVariable> tyres = new ArrayList<>();
    private TyreDataAdapter adapter;
    private ListView listView;
    private View rootView;


    // Initiate it All in below lines // Start {
    public UpdateUi(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
        listView = (ListView) rootView.findViewById(R.id.common_listview_layout);
    }

    public void addTyre(TyreDataVariable tyre) {
        this.tyres.add(tyre);
    }

    public void updateUIinside() {
        adapter = new TyreDataAdapter(context, tyres);
        listView.setAdapter(adapter);
    }

}
