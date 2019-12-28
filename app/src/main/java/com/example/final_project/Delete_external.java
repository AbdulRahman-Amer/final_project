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

public class Delete_external extends AppCompatActivity {

    // create object
    ArrayList<User> userList;
    ListView listView;
    User user;
    phpConn phpC = new phpConn();
    String localhost ="192.168.100.8";
    final String fetch = "http://"+localhost+"/sqli/fetch2json.php";// call php
    final String fetch1="http://"+localhost+"/sqli/delete.php?id=";

    JSONArray js_array;

    /** Called when the activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_external);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // one click for show data
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, long l) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Delete_external.this);

                // Setting Dialog Title
                alertDialog.setTitle("Confirm Delete...");

                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want delete this?");

                // Setting Icon to Dialog
                //alertDialog.setIcon(R.drawable.ic_launcher_background);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            int pos=position;
                            String curValue =js_array.getString(position);
                            Log.v("Array with ",curValue);

                            String value1 = curValue.substring(1, curValue.indexOf(","));
                            value1=value1.replace("\"" ,"");
                            Log.v("george value1 ",value1.toString());


                            Toast.makeText(getApplicationContext(), curValue, Toast.LENGTH_SHORT).show();

                            // phpC.urlCon(fetchName + "?"+getName);
                            phpC.urlCon(fetch1+value1);
                            userList.remove(pos);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Log.e("Error", e.toString());
                        }

                    }
                });
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                alertDialog.show();

            }

        });

    }
}
