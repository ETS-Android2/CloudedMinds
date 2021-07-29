package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CheckIt extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_it);
        final Vibrator vibe = (Vibrator) CheckIt.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive2);
        TextView dateReceive = findViewById(R.id.dateReceive2);
        TextView moodReceive = findViewById(R.id.moodReceive);
        TextView ratingReceive = findViewById(R.id.ratingReceive);
        RadioGroup catastrophising = findViewById(R.id.rdgCatastrophising);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                AlertDialog.Builder alert = new AlertDialog.Builder(CheckIt.this);
                alert.setTitle("Are you sure?");
                alert.setMessage("You will return to the Main Menu which will cause all your entered data to be removed. Are you sure you wish to do this?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(CheckIt.this, MainActivity.class));
                    }
                });
                alert.setNegativeButton("NO", null);
                alert.show();
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive2);
        TextView receiveDate = findViewById(R.id.dateReceive2);
        TextView receiveMood = findViewById(R.id.moodReceive);
        TextView receiveRating = findViewById(R.id.ratingReceive);
        TextView receiveUser = findViewById(R.id.receiveUser5);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event2_txt");
        String receivedDate = intent.getStringExtra("date2_txt");
        String receivedMood = intent.getStringExtra("mood_txt");
        String receivedRating = intent.getStringExtra("rating_txt");
        String receivedUser = intent.getStringExtra("username5");

        receiveUser.setText(receivedUser);
        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);
        receiveMood.setText(receivedMood);
        receiveRating.setText(receivedRating);

        Button checkit2 = findViewById(R.id.btnToCheckIt2);
        checkit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                String txtEvent = eventReceive.getText().toString();
                String txtDate = dateReceive.getText().toString();
                String txtMood = moodReceive.getText().toString();
                String txtRating = ratingReceive.getText().toString();
                int selectedId = catastrophising.getCheckedRadioButtonId();
                RadioButton catastrophised = findViewById(selectedId);
                String txtCatas= catastrophised.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CheckIt2.class);
                intent.putExtra("event3_txt", txtEvent);
                intent.putExtra("date3_txt", txtDate);
                intent.putExtra("mood2_txt", txtMood);
                intent.putExtra("rating2_txt", txtRating);
                intent.putExtra("catastrophised_txt", txtCatas);
                intent.putExtra("username6", receiveUser.getText().toString());
                startActivity(intent);
            }
        });

        checkit2.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        checkit2.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        checkit2.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });

    }
}