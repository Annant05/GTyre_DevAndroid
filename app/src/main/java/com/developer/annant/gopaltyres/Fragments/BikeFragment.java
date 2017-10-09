package com.developer.annant.gopaltyres.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.annant.gopaltyres.JsonAsyncTask;
import com.developer.annant.gopaltyres.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BikeFragment extends Fragment {


    public static final String LOG_TAG = BikeFragment.class.getSimpleName();
    final String JSON_REQUEST_URL = "https://api.myjson.com/bins/15nk89";  /// ENTER THE URL HERE
    final String JSON_FILE_NAME = "bikedatajson.json";
    //  Bundle args = new Bundle();

    public BikeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list_maker, container, false);

//        FirebaseStorage storage = FirebaseStorage.getInstance();



        new JsonAsyncTask(getActivity(), rootView, JSON_REQUEST_URL).execute();

        return rootView;
    }

}