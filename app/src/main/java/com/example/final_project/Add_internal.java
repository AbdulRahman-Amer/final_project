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
    DBHelper mydb;
    int s;
    Button btnAdd,btnback;
    EditText etFirstName,etLastName,etFavFood;

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextStreet;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_internal);

        mydb = new DBHelper(this);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnback = (Button) findViewById(R.id.btnback);
        etFavFood = (EditText) findViewById(R.id.etFavFood);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //remove the following toast...
                Toast.makeText(getApplicationContext(),
                        "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();

                String fName = etFirstName.getText().toString();
                String lName = etLastName.getText().toString();
                String fFood = etFavFood.getText().toString();
                if (fName.isEmpty()||lName.isEmpty()||fFood.isEmpty()){
                    Toast.makeText(getApplicationContext(),"must be insert",Toast.LENGTH_SHORT).show();
                }
                else
                if (!Patterns.EMAIL_ADDRESS.matcher(fFood).matches()){
                    Toast.makeText(getApplicationContext(),"not correct email",Toast.LENGTH_SHORT).show();

                }else

                if (mydb.insertContact(fName, lName, fFood)) {
                    Log.v("georgeLog", "Successfully inserted record to db");
                    Toast.makeText(getApplicationContext(),
                            "Inserted:" + fName + ", " + lName + "," + fFood, Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(Add_internal.this,Internal.class);
                startActivity(intent);
            }
        });
    }
}
