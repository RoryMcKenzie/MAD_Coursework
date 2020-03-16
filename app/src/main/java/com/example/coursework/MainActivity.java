package com.example.coursework;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements androidx.lifecycle.LifecycleOwner{

    private ListItemViewModel mListItemViewModel;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;
    public static final int RESULT_INVALID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates new RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Creates new ViewModel
        mListItemViewModel = new ViewModelProvider(this).get(ListItemViewModel.class);

        //Creates new adapter
        final MyAdapter mAdapter = new MyAdapter(this, mListItemViewModel);

        //Sets contents of recyclerView to the adapter
        recyclerView.setAdapter(mAdapter);

        //Adds dividers between items in RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) layoutManager).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        //Observer for LiveData returned by getAllItemsPriority()
        mListItemViewModel.getAllItemsPriority().observe(this, new Observer<List<ListItem>>() {
            @Override
            public void onChanged(@Nullable final List<ListItem> items) {
                // Update the cached copy of the items in the adapter.
                mAdapter.setListItems(items);
            }
        });

        FloatingActionButton add_item = findViewById(R.id.fab);

        add_item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityA = new Intent(MainActivity.this, Activity_Add_Item.class);
                startActivityForResult(activityA, NEW_ITEM_ACTIVITY_REQUEST_CODE);
            }
        });
    }


    //Replaces the action bar with new action bar with spinner
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        MenuItem mitem=menu.findItem(R.id.action_sort);
        Spinner spin =(Spinner) mitem.getActionView();
        setupSpinner(spin);
        return super.onCreateOptionsMenu(menu);
    }

    //Setting up the spinner in the action bar
    public void setupSpinner(Spinner spin){
        String[] items={"Sort by Highest Priority","Sort by Newest"};
        //wrap the items in the Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.actionbar_spinner, items);
        //assign adapter to the Spinner
        spin.setAdapter(adapter);
    }

    //When this activity is returned to from Activity_Add_Item,
    //Processes the returned data and adds to database (if possible)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ListItem listItem = new ListItem(data.getStringExtra(Activity_Add_Item.EXTRA_REPLY), data.getStringExtra(Activity_Add_Item.EXTRA_REPLY2), false, data.getIntExtra(Activity_Add_Item.EXTRA_REPLY3, 0));
            mListItemViewModel.insert(listItem);
        //RESULT_INVALID is returned if the title is empty, so that the toast doesn't appear when back is pressed
        } else  if(resultCode == RESULT_INVALID){
            Toast.makeText(
                    getApplicationContext(),
                    "Item has no title. Not added.",
                    Toast.LENGTH_LONG).show();
        }
    }
}