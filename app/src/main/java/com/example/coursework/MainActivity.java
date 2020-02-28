package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] myDataset = new String[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataset[0]= "Item 1";
        myDataset[1]= "Item 2";
        myDataset[2]= "Item 3";
        myDataset[3]= "Item 4";
        myDataset[4]= "Item 5";



        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);


        Button add_item = findViewById(R.id.button2);
        add_item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityA = new Intent(MainActivity.this, Activity_Add_Item.class);
                startActivity(activityA);
            }
        });
    }


}







