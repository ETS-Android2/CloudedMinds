package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * This class allows the user to enter the mood and rating which are stored with Shared Preferences
 * so that it can be retrieved in the end screen for saving.
 */

public class CatchIt3 extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_it3);
        final Vibrator vibe = (Vibrator) CatchIt3.this.getSystemService(Context.VIBRATOR_SERVICE);
        RadioGroup moodrating = findViewById(R.id.rdGroupMood);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                AlertDialog.Builder alert = new AlertDialog.Builder(CatchIt3.this);
                alert.setTitle("Return to Main Menu");
                alert.setMessage("You will return to the Main Menu which will cause all your entered data to be removed. Are you sure you wish to do this?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(CatchIt3.this, MainActivity.class));
                    }
                });
                alert.setNegativeButton("NO", null);
                alert.show();
            }
        });

        Button checkit = findViewById(R.id.btnToCheckIt);
        checkit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);

                int selectedId = moodrating.getCheckedRadioButtonId();
                RadioButton moodRate = findViewById(selectedId);
                String txtRating = moodRate.getText().toString();
                // Checks if fields are empty and allows user to move ahead when they've entered the details.
                if(txtRating.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(CatchIt3.this);
                    alert.setTitle("Empty Rating");
                    alert.setMessage("Please rate the strength of your mood.");
                    alert.setPositiveButton("OK",null);
                    alert.show();
                }
                else {
                    SharedPreferences sharedPref = getSharedPreferences("ENTRIES", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("rating", txtRating);
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(), CheckIt.class));
                }
            }
        });

        checkit.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        checkit.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        checkit.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}