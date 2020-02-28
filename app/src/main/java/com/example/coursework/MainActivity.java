package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> toDoListArray;
    public static ArrayAdapter<String> adapter;
    public static ListView toDoList;

    class Item
    {
        public String title;
        public String subtext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView toDoList = findViewById(R.id.todo_list);
        toDoListArray = new ArrayList<>();

         final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                toDoListArray);

         for (int i = 1; i <= 15; i++) {

            toDoListArray.add("Item " + i);
         }

        toDoList.setAdapter(adapter);

        Button add_test = (Button) findViewById(R.id.button3);
        add_test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.add("Test");
                toDoList.smoothScrollToPosition(toDoListArray.size());


                String fulltext = "";

                for(String text : toDoListArray)
                {
                    fulltext += text + ", ";
                }
                Log.e(getResources().getString(R.string.app_name), fulltext);

            }
        });


        Button add_item = findViewById(R.id.button2);
        add_item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityA = new Intent(MainActivity.this, Activity_Add_Item.class);
                startActivity(activityA);
            }
        });
    }
}









