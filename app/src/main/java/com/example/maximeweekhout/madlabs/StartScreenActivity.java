package com.example.maximeweekhout.madlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by MaximeWeekhout on 17-09-16.
 */
public class StartScreenActivity extends Activity {

    ImageView image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen_activity);

        image = (ImageView) findViewById(R.id.madLabsLogo);
        image.setImageResource(R.drawable.madlabslogo);
    }

    /**
     * Function called by button, move to AskView
     * @param v view
     */
    public void askViewButtonClicked(View v) {
        Intent k = new Intent(this, AskScreenActivity.class);
        startActivity(k);
    }
}
