package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_external extends AppCompatActivity {
    // create object button and edit text and call class php connection
    Button btnAdd,btnback;
    EditText etFirstName,etLastName,etFavFood;
    phpConn phpC = new phpConn();
    String localhost ="192.168.100.8";
   // final String fetch = "http://"+localhost+"/sqli/fetch2json.php";//call to php

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_external);

//Java code will link and load that button and convert it to concrete object
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnback = (Button) findViewById(R.id.btnback);
        etFavFood = (EditText) findViewById(R.id.etFavFood);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);

        /*
        * When the user clicks a button, the Button object receives an on-click event.
        *  To make click event work add android:onClick attribute to the Button element
        *  in your XML layout. The value for this attribute must be the name of the
        * method you want to call in response to a click event.
        */

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * Here you are getting text from etfirst and ersecomd and convert it into string using toString().
                getText(): Return the text that is displaying.
                toString(): Returns a string representation of the object.*/

                String fName = etFirstName.getText().toString();
                String lName = etLastName.getText().toString();
                String fFood = etFavFood.getText().toString();

                if(btnAdd.length() != 0 ){
                    // call php insert and do it code
                    phpC.execute("http://"+localhost+"/sqli/insert.php?field1-name="+fName+"&field2-name="+lName+"&field3-name="+fFood);

                    // empty the edit text
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
                // go to another screen
                Intent intent = new Intent(Add_external.this,External.class);
                startActivity(intent);
            }
        });
    }
}
