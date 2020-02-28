package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static ArrayList<String> myDataset = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 1; i <= 15; i++) {
            myDataset.add("Item " + i);
        }

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);


        Button add_test = (Button) findViewById(R.id.button3);
        add_test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDataset.add("Test");
                mAdapter.notifyItemInserted(myDataset.size() - 1);
                recyclerView.smoothScrollToPosition(myDataset.size());
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




