package com.developer.annant.gopaltyres.Database;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Annant on 29-12-2017.
 */

public abstract class GetDataFromFirebase {
    // private EditText editName;

    private final String TAG = this.getClass().getName();
    private Context mContext;

    //  private String displayName = "Default";
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ValueEventListener tyreListener;
    private ChildEventListener mChildEventListener;

    private TyreDataVariable globalDataVariable;

    private EditText editSize, editTread, editPrice;
    private TextView text_size, text_tread, text_price;
    private Button downloadButton, uploadButton;


    public GetDataFromFirebase() {


        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("tyres");
        Log.d(TAG, myRef.toString());


        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TyreDataVariable tyreTemp = dataSnapshot.getValue(TyreDataVariable.class);
                globalDataVariable = tyreTemp;
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


    // For uploading data in a new tree with name "tyres" in the database ;
    public void writeTyre(TyreDataVariable uploadData) {
        myRef.push().setValue(uploadData);
    }

    public TyreDataVariable getTyres() {
        return globalDataVariable;
    }

    public void setContext(Context context) {
        mContext = context;
    }
}
