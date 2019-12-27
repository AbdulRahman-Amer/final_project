package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Internal extends AppCompatActivity {
    DBHelper mydb;
    Button btnAdd,btnView,btnback,btndelet,btnsearch;
    EditText edSearch;
    static String answer1 = null;
    static public String getAnswer1(){return answer1;}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal);
        mydb = new DBHelper(this);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btnback = (Button) findViewById(R.id.btnback);
        btndelet = (Button) findViewById(R.id.btndelet);
        btnsearch = (Button) findViewById(R.id.btnsearch);
        edSearch = (EditText) findViewById(R.id.edSearch);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(Internal.this,ViewListContents.class);
                //   startActivity(intent);
                Log.v("georgeLog", "clicked on Result Button");
                ArrayList<String> fetchAll = new ArrayList<String>();
                fetchAll=mydb.getAllContacts();
                for (String a:fetchAll)
                    Log.v("georgeLog:", a.toString());
                Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                Log.v("georgeLog:", "intent executed");
                intent.putStringArrayListExtra("fetchAll",fetchAll);
                Log.v("georgeLog:","fetchALL executed");
                startActivity(intent);
                Log.v("georgeLog:", "startActivity executed");

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Internal.this,Add_internal.class);
                startActivity(intent);
            }
        });

        btndelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Internal.this,Delete_internal.class);
                startActivity(intent);

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Internal.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    answer1 = edSearch.getText().toString();
                if (answer1.length() == 0){
                    Toast.makeText(getApplicationContext(), "is Empty", Toast.LENGTH_SHORT).show();
                }else {

                    Intent intent = new Intent(Internal.this, search_inernal.class);
                    startActivity(intent);
                    Log.v("georgeLog", "clicked on Result Button");
                }
            }
        });
    }
}
