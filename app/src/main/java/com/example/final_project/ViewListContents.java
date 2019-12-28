package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {
    //create object
    ArrayList<User> userList;
    ListView listView;
    User user;
    phpConn phpC = new phpConn();
    final String fetch = "http://192.168.100.8/sqli/fetch2json.php";
    final String fetch1="http://192.168.100.8/sqli/delete.php?id=";

    JSONArray js_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);
        userList = new ArrayList<>();

        //Log.v("george getRet",getRet);

        try {
            phpC.urlCon(fetch);
//getting an array back
            js_array = new JSONArray(phpC.getAnswer());

            /* reading the JSON array line by line */

            for (int i = 0; i < js_array.length(); i++) {


                String value=js_array.getString(i);

                String value1 = value.substring(1, value.indexOf(","));
                value1=value1.replace("\"" ,"");
                Log.v("george value1 ",value1.toString());
                String remainder = value.substring(value.indexOf(",")+2, value.length());

                String value2 = remainder.substring( 0, remainder.indexOf(","));
                value2=value2.replace("\"" ,"");
                Log.v("george value2 ",value2.toString());
                String remainder1 = remainder.substring(remainder.indexOf(",")+2, remainder.length());

                String value3 = remainder1.substring( 0, remainder1.indexOf(","));
                value3=value3.replace("\"" ,"");
                Log.v("george value3 ",value3.toString());
                String remainder2 = remainder1.substring(remainder1.indexOf(",")+1, remainder1.length()-1);

                String value4 = remainder2.substring(0);
                value4=value4.replace("\"" ,"");
                Log.v("george value4 ",value4.toString());


                user = new User(value1,value2,value3,value4);
                userList.add(i,user);
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        final ThreeColumn_ListAdapter adapter = new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, userList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


    }
    }

