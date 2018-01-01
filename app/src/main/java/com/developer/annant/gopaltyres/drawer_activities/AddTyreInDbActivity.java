package com.developer.annant.gopaltyres.drawer_activities;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;
import com.developer.annant.gopaltyres.R;
import com.developer.annant.gopaltyres.databaseClasses.TyreContract.TyreEntry;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddTyreInDbActivity extends AppCompatActivity {

    // private EditText editName;

    private final static String TAG = "AddTyreInDbActivity";
    private Context mContext = this;

    //  private String displayName = "Default";
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ValueEventListener tyreListener;
    private ChildEventListener mChildEventListener;


    private TyreDataVariable globalDataVariable;
    private EditText editSize, editTread, editPrice;
    private TextView text_size, text_tread, text_price;
    private Button downloadButton, uploadButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtyre_firebase);

        initialiseViews();
        // Write a message to the database
        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference().child("tyres");
        //Log.d("Contact us Data Snap", myRef.toString());
        //attachDatabaseReadListener();


        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  writeTyreToFirebase(getTyre());
                addToSqlDB(getTyre());
            }
        });


    }

    private void addToSqlDB(TyreDataVariable tyre) {
        if (isTyreValid(tyre)) {
            Toast.makeText(mContext, "Valid " + "\n"
                            + tyre.getTyreSize() + "\n"
                            + tyre.getTreadName() + "\n"
                            + tyre.getPrice(),
                    Toast.LENGTH_SHORT).show();
            //setTyre(tyre);

            ContentValues values = new ContentValues();
            values.put(TyreEntry.COLUMN_TYRE_SIZE, tyre.getTyreSize());
            values.put(TyreEntry.COLUMN_TREAD_NAME, tyre.getTreadName());
            values.put(TyreEntry.COLUMN_PRICE, tyre.getPrice());

            Uri newUri = getContentResolver().insert(TyreEntry.CONTENT_URI, values);// send into database

            if (newUri == null) {
                // If the row ID is -1, then there was an error with insertion.
                Toast.makeText(mContext, "Error with saving tyre data", Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast with the row ID.
                Toast.makeText(mContext, "tyre saved in database", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        detachDatabaseListener();
    }

    // For uploading data in a new tree with name "tyres" in the database ;


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
                    setTyre(dataSnapshot.getValue(TyreDataVariable.class));
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


    private void writeTyreToFirebase(TyreDataVariable tyre) {
        myRef.push().setValue(tyre);
    }

    private TyreDataVariable getTyre() {
        String size = editSize.getText().toString().trim().toUpperCase();
        String tread = editTread.getText().toString().trim().toUpperCase();
        String price = editPrice.getText().toString().trim().toUpperCase();
        return new TyreDataVariable(size, tread, price);
    }

    private void setTyre(TyreDataVariable tdv) {
        text_tread.setText(tdv.getTreadName());
        text_price.setText(tdv.getPrice());
        text_size.setText(tdv.getTyreSize());
    }

    public boolean isTyreValid(TyreDataVariable tyreV) {

        boolean isTyrevalid = true;

        if (tyreV.getTyreSize().isEmpty()) {
            isTyrevalid = false;
            Toast.makeText(mContext, "Please Enter a valid size", Toast.LENGTH_SHORT).show();
        } else if (tyreV.getTreadName().isEmpty()) {
            isTyrevalid = false;
            Toast.makeText(mContext, "Please Enter a valid tread", Toast.LENGTH_SHORT).show();
        } else if (tyreV.getPrice().isEmpty()) {
            isTyrevalid = false;
            Toast.makeText(mContext, "Please Enter a valid price", Toast.LENGTH_SHORT).show();
        }
        return isTyrevalid;
    }

    private void initialiseViews() {
        editSize = findViewById(R.id.edit_size);
        editTread = findViewById(R.id.edit_tread);
        editPrice = findViewById(R.id.edit_price);

        text_size = findViewById(R.id.display_size);
        text_tread = findViewById(R.id.display_tread);
        text_price = findViewById(R.id.display_price);

        downloadButton = findViewById(R.id.button_download);
        uploadButton = findViewById(R.id.button_upload);

    }


}
