package com.developer.annant.gopaltyres.Drawer_Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.developer.annant.gopaltyres.R;

public  class ContactUsDrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        TextView textView = (TextView) findViewById(R.id.contact_us_textview);
        TextView sizetextView = (TextView) findViewById(R.id.tyre_size);
        TextView nametextView = (TextView) findViewById(R.id.tyre_name);
        TextView pricetextView = (TextView) findViewById(R.id.tyre_price);


    }



}
