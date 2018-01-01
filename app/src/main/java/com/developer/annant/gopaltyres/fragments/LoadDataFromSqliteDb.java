package com.developer.annant.gopaltyres.fragments;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.annant.gopaltyres.R;
import com.developer.annant.gopaltyres.databaseClasses.TyreContract.TyreEntry;
import com.developer.annant.gopaltyres.databaseClasses.TyreDbHelper;
import com.developer.annant.gopaltyres.databaseClasses.UpdateUi;
import com.developer.annant.gopaltyres.variables_adapters.TyreDataVariable;

/**
 * A simple {@link Fragment} subclass.
 */


public class LoadDataFromSqliteDb extends Fragment {

    private static final String TAG = "LoadDatabaseFragment";
    Context context;

    private TyreDbHelper mDbHelper;


    /*private GetDataFromFirebase mDatabase;
    private int tyreCount = 0;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ChildEventListener mChildEventListener;
*/
    //  private TyreDataAdapter adapter;
    //private ArrayList<TyreDataVariable> globalTyreDataArray = new ArrayList<>();
    // private ListView listView;
    private View rootView;
    private UpdateUi uUiL;
    //private TyreDataVariable globalDataVariable;


    public LoadDataFromSqliteDb() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();
        rootView = inflater.inflate(R.layout.activity_list_maker, container, false);
        //    listView = (ListView) rootView.findViewById(R.id.common_listview_layout);
        mDbHelper = new TyreDbHelper(context);
        uUiL = new UpdateUi(context, rootView);
        displayDatabaseInfo();
        uUiL.updateUIinside();
        return rootView;

    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        //  ArrayList<TyreDataVariable> dataVariable = new ;
        String[] projection = {
                TyreEntry._ID,
                TyreEntry.COLUMN_TYRE_SIZE,
                TyreEntry.COLUMN_TREAD_NAME,
                TyreEntry.COLUMN_PRICE
        };

        Cursor cursor = context.getContentResolver().query(TyreEntry.CONTENT_URI,
                projection, null, null, null);

        try {
            int idColumnIndex = cursor.getColumnIndex(TyreEntry._ID);
            int sizeColumnIndex = cursor.getColumnIndex(TyreEntry.COLUMN_TYRE_SIZE);
            int treadColumnIndex = cursor.getColumnIndex(TyreEntry.COLUMN_TREAD_NAME);
            int priceColumnIndex = cursor.getColumnIndex(TyreEntry.COLUMN_PRICE);


            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentSize = cursor.getString(sizeColumnIndex);
                String currentTread = cursor.getString(treadColumnIndex);
                String currentPrice = cursor.getString(priceColumnIndex);
/*
                Toast.makeText(context, "Toasting \n" + currentSize + "\t"
                        + currentTread + "\t"
                        + currentPrice, Toast.LENGTH_SHORT).show();
*/

                //uUiL.addTyre(new TyreDataVariable(currentSize, currentTread, currentPrice));
                //  globalTyreDataArray.add(new TyreDataVariable(currentSize, currentTread, currentPrice));
                uUiL.addTyre(new TyreDataVariable(currentSize, currentTread, currentPrice));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
//        uUiL.updateUIinside();
    }
}