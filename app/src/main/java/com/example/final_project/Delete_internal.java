package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Delete_internal extends AppCompatActivity {
    DBHelper myDB;
    ArrayList<User> userList;
    ListView listView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_internal);
        myDB = new DBHelper(this);

        userList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(Delete_internal.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                user = new User(data.getString(0),data.getString(1),data.getString(2),data.getString(3));
                userList.add(i,user);
                System.out.println(data.getString(0)+" "+data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                System.out.println(userList.get(i).getFirstName());
                i++;

            }
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, final View view, final int position, final long id) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Delete_internal.this);
                    final  int num = position;
                    new AlertDialog.Builder(Delete_internal.this).setIcon(android.R.drawable.ic_delete)
                            .setTitle("Confirm Delete...")
                            .setMessage("Are you sure you want delete this?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog,int which) {
                                //    String name=userList.get(num).getFirstName();
                                    String id=userList.get(num).gettid();
                            //myDB.deleteContactByName(name);
                            myDB.deleteContactByID(id);
                            Intent intent = new Intent(Delete_internal.this,Delete_internal.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("NO", null).show();

                }
            });

        }



    }
}
