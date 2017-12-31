package com.developer.annant.gopaltyres.Drawer_Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;
import com.developer.annant.gopaltyres.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddTyreToFirebaseActivity extends AppCompatActivity {

    // private EditText editName;

    private final static String TAG = "AddTyreToFirebaseActivity";
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
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("tyres");
        Log.d("Contact us Data Snap", myRef.toString());
        attachDatabaseReadListener();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeTyreToFirebase(getTyre());
            }
        });

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
        return new TyreDataVariable(
                editSize.getText().toString().trim().toUpperCase(),
                editTread.getText().toString().trim().toUpperCase(),
                editPrice.getText().toString().trim().toUpperCase());

    }

    private void setTyre(TyreDataVariable tdv) {
        text_tread.setText(tdv.getTreadName());
        text_price.setText(tdv.getPrice());
        text_size.setText(tdv.getTyreSize());
    }


    private void initialiseViews() {
        editSize = (EditText) findViewById(R.id.edit_size);
        editTread = (EditText) findViewById(R.id.edit_tread);
        editPrice = (EditText) findViewById(R.id.edit_price);

        text_size = (TextView) findViewById(R.id.display_size);
        text_tread = (TextView) findViewById(R.id.display_tread);
        text_price = (TextView) findViewById(R.id.display_price);

        downloadButton = (Button) findViewById(R.id.button_download);
        uploadButton = (Button) findViewById(R.id.button_upload);

    }


}
