package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

public class External extends AppCompatActivity {
    Button btnAdd,btnView,btnback,btndelet,btnsearch;
    EditText edSearch;
    phpConn phpC = new phpConn();
    String localhost ="192.168.100.8";
    final String fetch = "http://"+localhost+"/sqli/fetch2json.php";
    static String answer2 = null;
    static public String getAnswer2(){return answer2;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btnback = (Button) findViewById(R.id.btnback);
        btndelet = (Button) findViewById(R.id.btndelet);
        btnsearch = (Button) findViewById(R.id.btnsearch);
        edSearch = (EditText) findViewById(R.id.edSearch);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(External.this,ViewListContents.class);
                startActivity(intent);
                phpConn asyncall = new phpConn();
                String getRet = asyncall.execute(fetch);
                //Log.v("george getRet",getRet);
                try {
                    phpC.urlCon(fetch);
                    JSONArray js_array = new JSONArray(getRet);


                    /* reading the JSON array line by line */
                    for (int i = 0; i < js_array.length(); i++) {
                        String value=js_array.getString(i);
                        Log.v("george json", i+"="+value);



                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(External.this,Add_external.class);
                startActivity(intent);
            }
        });

        btndelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(External.this,Delete_external.class);
                startActivity(intent);
                phpConn asyncall = new phpConn();
                String getRet = asyncall.execute(fetch);
                //Log.v("george getRet",getRet);
                try {
                    phpC.urlCon(fetch);
                    JSONArray js_array = new JSONArray(getRet);


                    /* reading the JSON array line by line */
                    for (int i = 0; i < js_array.length(); i++) {
                        String value=js_array.getString(i);
                        Log.v("george json", i+"="+value);



                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(External.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answer2 = edSearch.getText().toString();
                if (answer2.length() == 0){
                    Toast.makeText(getApplicationContext(), "is Empty", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(External.this, search_external.class);
                    startActivity(intent);
                    Log.v("serch external", answer2);
                }


            }


        });

    }
}
