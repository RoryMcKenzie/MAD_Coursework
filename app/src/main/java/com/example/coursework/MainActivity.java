package com.example.coursework;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListItemViewModel mListItemViewModel;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final MyAdapter mAdapter = new MyAdapter(this);
        recyclerView.setAdapter(mAdapter);

        mListItemViewModel = new ViewModelProvider(this).get(ListItemViewModel.class);

        //Observer for LiveData returned by getAllItems()
        mListItemViewModel.getAllItems().observe(this, new Observer<List<ListItem>>() {
            @Override
            public void onChanged(@Nullable final List<ListItem> items) {
                // Update the cached copy of the items in the adapter.
                mAdapter.setListItems(items);
            }
        });

        Button add_item = findViewById(R.id.button2);
        add_item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityA = new Intent(MainActivity.this, Activity_Add_Item.class);
                startActivityForResult(activityA, NEW_ITEM_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ListItem listItem = new ListItem(data.getStringExtra(Activity_Add_Item.EXTRA_REPLY), data.getStringExtra(Activity_Add_Item.EXTRA_REPLY2), false);
            mListItemViewModel.insert(listItem);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Item is empty. Not added.",
                    Toast.LENGTH_LONG).show();
        }
    }
}





