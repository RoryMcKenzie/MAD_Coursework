package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Activity_Add_Item extends AppCompatActivity {

    //Unique tags for intent replies
    public static final String EXTRA_REPLY = "com.example.android.todolist.REPLY";
    public static final String EXTRA_REPLY2 = "com.example.android.todolist.REPLY2";

    public static final int RESULT_INVALID = 2;



    private EditText mEditTitle;
    private EditText mEditNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__item);
        mEditTitle = findViewById(R.id.et_title);
        mEditNote = findViewById(R.id.et_note);

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.fab_save);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTitle.getText())) {
                    setResult(RESULT_INVALID, replyIntent);
                } else {
                    //Maybe should use serialiser instead of just having putExtra twice

                    String title = mEditTitle.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, title);

                    String note = mEditNote.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY2, note);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
