package com.example.maximeweekhout.madlabs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_screen_activity);

        this.context = getApplicationContext();


    }



}
