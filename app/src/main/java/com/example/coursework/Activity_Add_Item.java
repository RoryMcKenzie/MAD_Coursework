package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Activity_Add_Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__item);


        Button add = (Button) findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(Activity_Add_Item.this, "Feature not yet implemented", Toast.LENGTH_SHORT).show();
                finish();
            }


        });


    }

}
