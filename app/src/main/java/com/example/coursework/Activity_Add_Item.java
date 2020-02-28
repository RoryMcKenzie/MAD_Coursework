package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Activity_Add_Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__item);


        Button add = (Button) findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                //Doesn't work, cannot access adapter from this activity

                /*
                String fulltext = "";

                for(String text : MainActivity.toDoListArray)
                {
                    fulltext += text + ", ";
                }
                Log.d(getResources().getString(R.string.app_name), fulltext);

                MainActivity.adapter.add("Hello");


                 */
                finish();
            }


        });


    }

}
