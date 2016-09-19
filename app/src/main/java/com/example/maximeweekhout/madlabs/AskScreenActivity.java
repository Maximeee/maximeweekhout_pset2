package com.example.maximeweekhout.madlabs;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Random;

/**
 * Created by MaximeWeekhout on 17-09-16.
 */
public class AskScreenActivity extends Activity {

    private RetainedFragment dataFragment;

    private Context context;
    private Story story;

    // Views
    private TextView wordsRemaining, questionTextView;
    private EditText inputEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_screen_activity);

        this.context = getApplicationContext();

        // find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        dataFragment = (RetainedFragment) fm.findFragmentByTag("story");

        // create the fragment and data the first time
        if (dataFragment == null) {
            // add the fragment
            dataFragment = new RetainedFragment();
            fm.beginTransaction().add(dataFragment, "story").commit();

            // load the data from the web
            story = this.getRandomStoryFile();
            dataFragment.setData(story);
        } else {
            story = dataFragment.getData();
        }

        // Load views
        wordsRemaining = (TextView) findViewById(R.id.wordsRemainingTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);

        inputEditText = (EditText) findViewById(R.id.inputEditText);
        inputEditText.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            handleInput();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        this.updateTextViews();
    }

    /**
     * Loads a random story file. Automaticly loads other file when error encounters
     * @return Story class
     */
    private Story getRandomStoryFile() {

        String[] files = {"madlib0_simple", "madlib1_tarzan",
                "madlib2_university", "madlib3_clothes", "madlib4_dance"};

        AssetManager mngr = context.getAssets();

        do {
            int rnd = new Random().nextInt(files.length);

            try {
                InputStream is = mngr.open(files[rnd] + ".txt");
                return new Story(is);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        while (true);
    }

    /**
     * Get input from EditText field, and adds it to the stoy class
     */
    private void handleInput() {
        inputEditText.getText();
        if (inputEditText.getText().length() != 0) {

            story.fillInPlaceholder(inputEditText.getText().toString());

            updateTextViews();

            if (story.getPlaceholderRemainingCount() == 0) {
                goToStoryActivity();
            } else {
                Toast.makeText(this, "Great! Keep going!", Toast.LENGTH_SHORT).show();
            }
        } else {
            System.out.println("Empty!");
        }
    }

    /**
     * Update textviews with current information
     */
    private void updateTextViews() {
        wordsRemaining.setText(story.getPlaceholderRemainingCount() + " word(s) remaining");
        questionTextView.setText("Please insert a(n) " + story.getNextPlaceholder().toLowerCase());
        inputEditText.setText("");
    }

    /**
     * Open StoryActivity and send story string with it
     */
    private void goToStoryActivity() {
        Intent k = new Intent(this, StoryScreenActivity.class);
        k.putExtra("story", story.toString());
        startActivity(k);
    }

    /**
     * Function called by button, call handleInput()
     * @param v view
     */
    public void nextButtonClicked(View v) {
        this.handleInput();
    }

    /**
     * Store story class in fragment. Called when screen config changes
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        // store the data in the fragment
        dataFragment.setData(story);
    }
}