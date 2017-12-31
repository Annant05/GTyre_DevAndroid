package com.developer.annant.gopaltyres.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.developer.annant.gopaltyres.Extras_imp.TyreDataAdapter;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;
import com.developer.annant.gopaltyres.R;
import com.developer.annant.gopaltyres.databaseClasses.GetDataFromFirebase;
import com.developer.annant.gopaltyres.databaseClasses.TyreProvider;
import com.developer.annant.gopaltyres.databaseClasses.UpdateUi;
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
    private TyreProvider mdb;
    private GetDataFromFirebase mDatabase;
    private int tyreCount = 0;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ChildEventListener mChildEventListener;

    private TyreDataAdapter adapter;
    private ArrayList<TyreDataVariable> globalTyreDataVariables;
    private ListView listView;
    private View rootView;
    private UpdateUi uUiL;
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
        rootView = inflater.inflate(R.layout.activity_list_maker, container, false);
        uUiL = new UpdateUi(context, rootView);


        //listView = (ListView) rootView.findViewById(R.id.common_listview_layout);

        // Write a message to the database
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("tyres");
        //  myRef.keepSynced(true);
        attachDatabaseReadListener();
        Log.d(TAG, myRef.toString());

        return rootView;

    }

    private void addToUuil(DataSnapshot dataSnapshot) {
        uUiL.addTyre(dataSnapshot.getValue(TyreDataVariable.class));
        uUiL.updateUIinside();
    }

    // For uploading data in a new tree with name "tyres" in the database ;
    public void writeTyre(TyreDataVariable uploadData) {
        myRef.push().setValue(uploadData);
    }


    private void detachDatabaseListener() {
        if (mChildEventListener != null) {
            myRef.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    addToUuil(dataSnapshot);
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
        }
    }

}