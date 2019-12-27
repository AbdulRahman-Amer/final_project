package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    Button bttnex,bttnen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bttnex = (Button) findViewById(R.id.bttnex);
        bttnen = (Button) findViewById(R.id.bttnen);

        bttnex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,External.class);
                startActivity(intent);

            }
        });

        bttnen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(getApplicationContext(),Internal.class);
                Intent intent = new Intent(MainActivity.this,Internal.class);
                startActivity(intent);

            }
        });
    }
}
