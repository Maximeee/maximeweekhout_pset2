package com.example.maximeweekhout.madlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

/**
 * Created by MaximeWeekhout on 17-09-16.
 */
public class StoryScreenActivity extends Activity {

    TextView storyTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_screen_activity);

        storyTextView = (TextView) findViewById(R.id.storyTextView);
        storyTextView.setMovementMethod(new ScrollingMovementMethod());

        Bundle extras = getIntent().getExtras();
        String message = extras.getString("story");

        storyTextView.setText(message);
    }

    /**
     * Function called by button, call moveToMain()
     * @param v view
     */
    void restartButtonClicked(View v) {
        moveToMain();
    }

    /**
     * Handle backpress for restarting app
     */
    @Override
    public void onBackPressed() {
        moveToMain();
    }

    /**
     * Open Story activity and clear viewstack
     */
    void moveToMain() {
        Intent k = new Intent(this, StartScreenActivity.class);
        k.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(k);
    }
}
