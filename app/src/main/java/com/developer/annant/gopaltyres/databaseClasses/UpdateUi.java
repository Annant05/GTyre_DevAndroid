package com.developer.annant.gopaltyres.databaseClasses;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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
        listView = this.rootView.findViewById(R.id.common_listview_layout);
    }

    public void addManyTyres(ArrayList<TyreDataVariable> tyresArg) {
        tyres.addAll(tyresArg);
    }

    public void addTyre(TyreDataVariable tyre) {
        this.tyres.add(tyre);
    }

    public void updateUIinside() {

        if (this.tyres != null) {
            adapter = new TyreDataAdapter(context, tyres);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(context, " Hmm, no data in Arraylist_Tyres ", Toast.LENGTH_LONG).show();
        }
    }
}
