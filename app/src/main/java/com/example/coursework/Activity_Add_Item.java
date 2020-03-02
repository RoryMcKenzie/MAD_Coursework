package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_Add_Item extends AppCompatActivity {

    //Unique tags for intent replies
    public static final String EXTRA_REPLY = "com.example.android.todolist.REPLY";
    public static final String EXTRA_REPLY2 = "com.example.android.todolist.REPLY2";



    private EditText mEditTitle;
    private EditText mEditNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__item);
        mEditTitle = findViewById(R.id.et_title);
        mEditNote = findViewById(R.id.et_note);


        Button add = (Button) findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTitle.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String title = mEditTitle.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, title);
                    setResult(RESULT_OK, replyIntent);

                    String note = mEditNote.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY2, note);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
