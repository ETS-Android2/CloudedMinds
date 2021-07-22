package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

public class CatchIt2 extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_it2);
        final Vibrator vibe = (Vibrator) CatchIt2.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.txtEventReceive);
        TextView dateReceive = findViewById(R.id.txtDateReceive);
        EditText mood = findViewById(R.id.txtMood);
        RadioGroup moodrating = findViewById(R.id.rdGroupMood);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(CatchIt2.this, MainActivity.class));
            }
        });

        TextView receiveEvent = findViewById(R.id.txtEventReceive);
        TextView receiveDate = findViewById(R.id.txtDateReceive);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event_txt");
        String receivedDate = intent.getStringExtra("date_txt");
        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);

        Button checkit = findViewById(R.id.btnToCheckIt);
        checkit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                String txtEventReceive = eventReceive.getText().toString();
                String txtDateReceive = dateReceive.getText().toString();
                String txtMood = mood.getText().toString();
                int selectedId = moodrating.getCheckedRadioButtonId();
                RadioButton moodRate = findViewById(selectedId);
                String txtRating = moodRate.getText().toString();
                // Checks if fields are empty and allows user to move ahead when they've entered the details.
                if(txtMood.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(CatchIt2.this);
                    alert.setTitle("Empty Mood");
                    alert.setMessage("Please enter the mood you experienced.");
                    alert.setPositiveButton("OK",null);
                    alert.show();
                }
                else if(txtRating.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(CatchIt2.this);
                    alert.setTitle("Empty Rating");
                    alert.setMessage("Please rate the strength of your mood.");
                    alert.setPositiveButton("OK",null);
                    alert.show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), CheckIt.class);
                    intent.putExtra("event2_txt", txtEventReceive);
                    intent.putExtra("date2_txt", txtDateReceive);
                    intent.putExtra("mood_txt", txtMood);
                    intent.putExtra("rating_txt", txtRating);
                    startActivity(intent);
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