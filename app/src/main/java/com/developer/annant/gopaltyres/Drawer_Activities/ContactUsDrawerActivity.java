package com.developer.annant.gopaltyres.Drawer_Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;
import com.developer.annant.gopaltyres.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactUsDrawerActivity extends AppCompatActivity {

    // private EditText editName;

    private Context mContext = this;
    private final static String TAG = "ContactUsDrawerActivity";

    //  private String displayName = "Default";

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private EditText editSize, editTread, editPrice;
    private TextView text_size, text_tread, text_price;
    private Button downloadButton, uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        editSize = (EditText) findViewById(R.id.edit_size);
        editTread = (EditText) findViewById(R.id.edit_tread);
        editPrice = (EditText) findViewById(R.id.edit_price);


        text_size = (TextView) findViewById(R.id.display_size);
        text_tread = (TextView) findViewById(R.id.display_tread);
        text_price = (TextView) findViewById(R.id.display_price);

        downloadButton = (Button) findViewById(R.id.button_download);
        uploadButton = (Button) findViewById(R.id.button_upload);


        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        ValueEventListener tyreListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                TyreDataVariable readTyres = dataSnapshot.getValue(TyreDataVariable.class);

                /*
                String value = dataSnapshot.getValue(String.class);
                */
                Toast.makeText(mContext, readTyres.getTreadName().toUpperCase(), Toast.LENGTH_SHORT).show();
                //               Log.d(TAG, "Value is: " + );
                displayTyre(readTyres.getTyreSize(), readTyres.getTreadName(), readTyres.getPrice());

            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(mContext, "Event Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDatabase();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeTyre(editSize.getText().toString(), editTread.getText().toString(), editPrice.getText().toString());
            }
        });

    }

    private void writeTyre(String size, String tread, String price) {
        TyreDataVariable tyres = new TyreDataVariable(size, tread, price);
        myRef.child("Tyres").setValue(tyres);
    }

    private void getDatabase() {
        myRef.addValueEventListener(

    }

    private void displayTyre(String size, String tread, String price) {
        text_tread.setText(tread);
        text_price.setText(price);
        text_size.setText(size);
    }
}
