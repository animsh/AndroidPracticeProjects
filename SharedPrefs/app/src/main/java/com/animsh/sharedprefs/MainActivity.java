package com.animsh.sharedprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String ID = "MyPref";
    EditText data;
    TextView savedData;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.message_et);
        savedData = findViewById(R.id.saved_tv);
        saveBtn = findViewById(R.id.save_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = data.getText().toString().trim();
                SharedPreferences sharedPreferences = getSharedPreferences(ID,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("message",message);
                editor.apply();

                String saved = sharedPreferences.getString("message","");
                savedData.setText(saved);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(ID,MODE_PRIVATE);
        String saved = sharedPreferences.getString("message","");
        savedData.setText(saved);    }
}