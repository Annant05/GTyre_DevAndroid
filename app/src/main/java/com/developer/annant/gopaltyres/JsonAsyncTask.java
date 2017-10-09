package com.developer.annant.gopaltyres;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.developer.annant.gopaltyres.Extras_imp.TyreDataAdapter;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.developer.annant.gopaltyres.Fragments.BikeFragment.LOG_TAG;

/**
 * Created by PCEH on 19-03-2017.
 */

// Async Task Below

public class JsonAsyncTask extends AsyncTask<URL, Void, ArrayList<TyreDataVariable>> {

    private ArrayList<TyreDataVariable> tyreDataReturn = new ArrayList<>();
    private Context mContext;
    private View rootView;
    private String JSON_REQUEST_URL;

    public JsonAsyncTask(Context context, View rootView, String JSON_url) {
        this.mContext = context;
        this.rootView = rootView;
        this.JSON_REQUEST_URL = JSON_url;
    }


    @Override
    protected ArrayList<TyreDataVariable> doInBackground(URL... urls) {
        URL url = createUrl(JSON_REQUEST_URL);     //Step 1
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);   //Step 2
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "error In do In Background method ", Toast.LENGTH_LONG).show();
            // TODO Handle the IOException
        }
        // BikeFragment bikeFragment = new BikeFragment();
        return  parseJsonData(jsonResponse);
    }


    @Override
    protected void onPostExecute(ArrayList<TyreDataVariable> tyreDataVariables) {
        //super.onPostExecute(tyreDataVariables);

        updateUi(tyreDataVariables);    //Refer Step 4

    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(mContext, "PLease , Wait \nFetching Json data from Internet ", Toast.LENGTH_LONG).show();
    }


    //Below are Custom Functions

    //  First Function Refer Step 1 ;

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }


    // Second Function Refer Step 2

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);          // milliseconds
            urlConnection.setConnectTimeout(15000);        // milliseconds
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);                 // Step 3
            } else {
                jsonResponse = "";
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "error getting InputStrean", e);
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    // Third Function Refer Step 3

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    //     Fourth Function Refer Step 4

    private void updateUi(ArrayList<TyreDataVariable> tyreDataVariables) {

        ListView TyreDatalistView = (ListView) rootView.findViewById(R.id.common_listview_layout);
        final TyreDataAdapter adapter = new TyreDataAdapter(mContext, tyreDataVariables);
        TyreDatalistView.setAdapter(adapter);
    }


    // this method is a part of BikeFragment Class of parent class
    // Verified Methos **Do not Edit** //  Works on Global VAriable of type  ArrayList //  Verified 5 Times

    /**
     * STRICTLY DON'T EDIT
     */

    private ArrayList<TyreDataVariable> parseJsonData(String JsonResponse) {
        try {
            JSONObject jsonRootObject = new JSONObject(JsonResponse);
            JSONArray tyreArray = jsonRootObject.getJSONArray("tyre");

            for (int i = 0; i < tyreArray.length(); i++) {
                JSONObject currentIndex = tyreArray.getJSONObject(i);

                String tyreSize = currentIndex.getString("size");
                String tyreTreadName = currentIndex.getString("name");
                long tyrePrice = currentIndex.getLong("price");

                TyreDataVariable tyreDataVariable = new TyreDataVariable(tyreSize, tyreTreadName, String.valueOf(tyrePrice));
                tyreDataReturn.add(tyreDataVariable);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText( this, "THis Is JSON EXCEPTION ", Toast.LENGTH_LONG).show();
        }
        return tyreDataReturn;
    }  //end parseJson Data Or array List maker Global


}//End {@link AsyncTask}


