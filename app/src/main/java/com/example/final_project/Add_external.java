package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_external extends AppCompatActivity {
    Button btnAdd,btnback;
    EditText etFirstName,etLastName,etFavFood;
    phpConn phpC = new phpConn();
    String localhost ="192.168.100.8";
    final String fetch = "http://"+localhost+"/sqli/fetch2json.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_external);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnback = (Button) findViewById(R.id.btnback);
        etFavFood = (EditText) findViewById(R.id.etFavFood);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = etFirstName.getText().toString();
                String lName = etLastName.getText().toString();
                String fFood = etFavFood.getText().toString();

                if(btnAdd.length() != 0 ){
                    phpC.execute("http://"+localhost+"/sqli/insert.php?field1-name="+fName+"&field2-name="+lName+"&field3-name="+fFood);
                    etFirstName.setText("");
                    etLastName.setText("");
                    etFavFood.setText("");
                    Toast.makeText(Add_external.this,"Successfully Entered Data!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Add_external.this,"You must put something in the text field!",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_external.this,External.class);
                startActivity(intent);
            }
        });
    }
}
