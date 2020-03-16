package com.example.coursework;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Activity_Add_Item extends AppCompatActivity {

    //Unique tags for intent replies
    public static final String EXTRA_REPLY = "com.example.android.todolist.REPLY";
    public static final String EXTRA_REPLY2 = "com.example.android.todolist.REPLY2";
    public static final String EXTRA_REPLY3 = "com.example.android.todolist.REPLY3";

    public static final int RESULT_INVALID = 2;

    private EditText mEditTitle;
    private EditText mEditNote;
    private Spinner mPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__item);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Item");
        mEditTitle = findViewById(R.id.et_title);
        mEditNote = findViewById(R.id.et_note);
        mPriority =  findViewById(R.id.spinner_priority);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priorities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPriority.setAdapter(adapter);
        //Sets default selection of spinner to 'None'
        mPriority.setSelection(3);


        //Sends the inputted data back to the Activity from which it was launched
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.fab_save);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTitle.getText())) {
                    setResult(RESULT_INVALID, replyIntent);
                } else {
                    String title = mEditTitle.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, title);

                    String note = mEditNote.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY2, note);

                    int priority;

                    switch(mPriority.getSelectedItem().toString()){
                        case "High": priority = 3;
                            break;
                        case "Medium": priority = 2;
                            break;
                        case "Low": priority = 1;
                            break;
                        default: priority = 0;
                            break;
                    }
                    replyIntent.putExtra(EXTRA_REPLY3, priority);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
