package com.developer.annant.gopaltyres;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.Map;

public class RemoteConfigTest extends AppCompatActivity {
    private final static String VARIABLE_NAME = "dear_variable";
    FirebaseRemoteConfig mFirebaseRemoteConfig;
    String remoteString;
    Map<String, Object> mMap;
    private TextView remoteText;
    private Button buttonUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_config_test);
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        remoteText = (TextView) findViewById(R.id.remote_text);
        buttonUpdate = (Button) findViewById(R.id.button_update_config);
        //remoteVariableMap
        //mMap.put("remoteConfigVar", remoteString);
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(BuildConfig.DEBUG).build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchWelcome();
            }
        });

    }

    private void fetchWelcome() {
        remoteText.setText(mFirebaseRemoteConfig.getString(VARIABLE_NAME));

        long cacheExpiration = 3600; // 1 hour in seconds.
        // If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
        // retrieve values from the service.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        // [START fetch_config_with_callback]
        // cacheExpirationSeconds is set to cacheExpiration here, indicating the next fetch request
        // will use fetch data from the Remote Config service, rather than cached parameter values,
        // if cached parameter values are more than cacheExpiration seconds old.
        // See Best Practices in the README for more information.
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RemoteConfigTest.this, "Fetch Succeeded",
                                    Toast.LENGTH_SHORT).show();

                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(RemoteConfigTest.this, "Fetch Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        doSomething();
                    }
                });
        // [END fetch_config_with_callback]
    }

    private void doSomething() {
        remoteText.setText(mFirebaseRemoteConfig.getString(VARIABLE_NAME));
    }

}