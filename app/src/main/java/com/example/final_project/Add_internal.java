package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_internal extends AppCompatActivity {
    // create object button and edit text and call class  connection
    DBHelper mydb;
    int s;
    Button btnAdd,btnback;
    EditText etFirstName,etLastName,etFavFood;

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextStreet;


    /** Called when the activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_internal);

        mydb = new DBHelper(this);

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
            public void onClick(View v) {
                //remove the following toast...
                Toast.makeText(getApplicationContext(),
                        "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();
/*
                * Here you are getting text from etfirst and ersecomd and convert it into string using toString().
                getText(): Return the text that is displaying.
                toString(): Returns a string representation of the object.*/

                String fName = etFirstName.getText().toString();
                String lName = etLastName.getText().toString();
                String fFood = etFavFood.getText().toString();
                if (fName.isEmpty()||lName.isEmpty()||fFood.isEmpty()){
                    Toast.makeText(getApplicationContext(),"must be insert",Toast.LENGTH_SHORT).show();
                }
                else
                if (!Patterns.EMAIL_ADDRESS.matcher(fFood).matches()){//Checks for validity Email
                    Toast.makeText(getApplicationContext(),"not correct email",Toast.LENGTH_SHORT).show();

                }else

                if (mydb.insertContact(fName, lName, fFood)) {// if add everthing do it all under
                    Log.v("georgeLog", "Successfully inserted record to db");
                    Toast.makeText(getApplicationContext(),
                            "Inserted:" + fName + ", " + lName + "," + fFood, Toast.LENGTH_SHORT).show();
                    // get empty all edit text
                            etFirstName.setText("");
                            etLastName.setText("");
                            etFavFood.setText("");

                } else
                    Toast.makeText(getApplicationContext(), "DID NOT insert to db :-(", Toast.LENGTH_SHORT).show();
            }

        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to another screen
                Intent intent = new Intent(Add_internal.this,Internal.class);
                startActivity(intent);
            }
        });
    }
}
