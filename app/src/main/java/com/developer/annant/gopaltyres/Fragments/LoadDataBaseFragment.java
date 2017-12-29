package com.developer.annant.gopaltyres.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.developer.annant.gopaltyres.Database.DataHelper;
import com.developer.annant.gopaltyres.Database.GetDataFromFirebase;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataAdapter;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;
import com.developer.annant.gopaltyres.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */


public class LoadDataBaseFragment extends Fragment {

    private static final String TAG = "LoadDatabaseFragment";
    Context context;
    private DataHelper mdb;
    private GetDataFromFirebase mDatabase;
    private int tyreCount = 0;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ChildEventListener mChildEventListener;

    private TyreDataAdapter adapter;
    private ArrayList<TyreDataVariable> globalTyreDataVariables;
    private ListView listView;
    //private TyreDataVariable globalDataVariable;


    public LoadDataBaseFragment() {
        // Required empty public constructor
    }

    public void addOneMoreTyre(TyreDataVariable tyre) {
        globalTyreDataVariables.add(tyre);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();

        View rootView = inflater.inflate(R.layout.activity_list_maker, container, false);
        listView = (ListView) rootView.findViewById(R.id.common_listview_layout);

        globalTyreDataVariables = new ArrayList<>();


        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("tyres");

        Log.d(TAG, myRef.toString());


        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addToGlobalDataVariable(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addChildEventListener(mChildEventListener);


        /*tyreDataVariables.add(new TyreDataVariable("Annant", "Gupta", "Free"));
        tyreDataVariables.add(new TyreDataVariable("Annant", "Gupta", "Free"));

        Toast.makeText(context, "Once Again", Toast.LENGTH_LONG).show();

        /*
         databaseFirebase();

        tyreDataVariable.add(new TyreDataVariable("Enter", "Into", "FREE"));

        mdb = new DataHelper(getActivity());
        mdb.addTyre(tyreDataVariable.get(0));

        TyreDataAdapter adapter = new TyreDataAdapter(getActivity(), tyreDataVariable);
        ListView listView = (ListView) rootView.findViewById(R.id.common_listview_layout);
        listView.setAdapter(adapter);



        tyreDataVariable.add(new TyreDataVariable("HEllo", "World","FREE" ));
*/

        //mDatabase.writeTyre(tyreDataVariable.get(0));
        //tyreDataVariables.add(new TyreDataVariable(mDatabase.getTyres().getTyreSize(), mDatabase.getTyres().getTreadName(), mDatabase.getTyres().getPrice()));

        updateUI();
        return rootView;

    }

    private void addToGlobalDataVariable(DataSnapshot dataSnapshot) {
        globalTyreDataVariables.add(dataSnapshot.getValue(TyreDataVariable.class));
//        mdb.addTyre(globalTyreDataVariables.get(tyreCount++));
        updateUI();
    }

    private void updateUI() {
        /*mdb = new DataHelper(getActivity());
        for (TyreDataVariable i : globalTyreDataVariables) {
            mdb.addTyre(i);

        }
        */
        adapter = new TyreDataAdapter(getActivity(), globalTyreDataVariables);
        listView.setAdapter(adapter);
    }

    // For uploading data in a new tree with name "tyres" in the database ;
    public void writeTyre(TyreDataVariable uploadData) {
        myRef.push().setValue(uploadData);
    }


}