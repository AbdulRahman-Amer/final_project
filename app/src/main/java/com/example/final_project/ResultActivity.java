package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    DBHelper myDB;
    ArrayList<User> userList;
    ListView listView;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        myDB = new DBHelper(this);

        userList = new ArrayList<>();
        Cursor data = myDB.getListContents();

        int numRows = data.getCount();
        //check row
        if(numRows == 0){
            Toast.makeText(ResultActivity.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            // moveToFirst is used when you need to start iterating from start after you have already reached some position.
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
        }

    }
}
