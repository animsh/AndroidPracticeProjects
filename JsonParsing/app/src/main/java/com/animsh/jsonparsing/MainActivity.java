package com.animsh.jsonparsing;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    String TAG = "JSON_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            JSONObject object = new JSONObject(readJSON());
            for (int i = 1; i < 800; i++) {
                JSONArray one = object.getJSONArray(String.valueOf(i));
                if (one.length() == 1) {
                    Log.e(TAG, one.getJSONObject(0).getJSONObject("type").getString("name"));
                } else if (one.length() == 2) {
                    Log.e(TAG, one.getJSONObject(0).getJSONObject("type").getString("name") + " "
                            + one.getJSONObject(1).getJSONObject("type").getString("name"));
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // to read json files from assets folder
    public String readJSON() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("test.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }
}