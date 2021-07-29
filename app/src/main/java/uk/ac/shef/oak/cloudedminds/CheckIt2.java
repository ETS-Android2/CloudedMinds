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

public class CheckIt2 extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_it2);
        final Vibrator vibe = (Vibrator) CheckIt2.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive3);
        TextView dateReceive = findViewById(R.id.dateReceive3);
        TextView moodReceive = findViewById(R.id.moodReceive2);
        TextView ratingReceive = findViewById(R.id.ratingReceive2);
        TextView catasReceive = findViewById(R.id.catasReceive);
        RadioGroup generalising = findViewById(R.id.rdgOverGeneralising);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                AlertDialog.Builder alert = new AlertDialog.Builder(CheckIt2.this);
                alert.setTitle("Are you sure?");
                alert.setMessage("You will return to the Main Menu which will cause all your entered data to be removed. Are you sure you wish to do this?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(CheckIt2.this, MainActivity.class));
                    }
                });
                alert.setNegativeButton("NO", null);
                alert.show();
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive3);
        TextView receiveDate = findViewById(R.id.dateReceive3);
        TextView receiveMood = findViewById(R.id.moodReceive2);
        TextView receiveRating = findViewById(R.id.ratingReceive2);
        TextView receiveCatas = findViewById(R.id.catasReceive);
        TextView receiveUser = findViewById(R.id.receiveUser6);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event3_txt");
        String receivedDate = intent.getStringExtra("date3_txt");
        String receivedMood = intent.getStringExtra("mood2_txt");
        String receivedRating = intent.getStringExtra("rating2_txt");
        String receivedCatas = intent.getStringExtra("catastrophised_txt");
        String receivedUser = intent.getStringExtra("username6");

        receiveUser.setText(receivedUser);
        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);
        receiveMood.setText(receivedMood);
        receiveRating.setText(receivedRating);
        receiveCatas.setText(receivedCatas);

        Button checkit3 = findViewById(R.id.btnToCheckIt3);
        checkit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                String txtEvent = eventReceive.getText().toString();
                String txtDate = dateReceive.getText().toString();
                String txtMood = moodReceive.getText().toString();
                String txtRating = ratingReceive.getText().toString();
                String txtCatas = catasReceive.getText().toString();
                int selectedId = generalising.getCheckedRadioButtonId();
                RadioButton generalised = findViewById(selectedId);
                String txtGene = generalised.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CheckIt3.class);
                intent.putExtra("event4_txt", txtEvent);
                intent.putExtra("date4_txt", txtDate);
                intent.putExtra("mood3_txt", txtMood);
                intent.putExtra("rating3_txt", txtRating);
                intent.putExtra("catastrophised2_txt", txtCatas);
                intent.putExtra("generalised_txt", txtGene);
                intent.putExtra("username7", receiveUser.getText().toString());
                startActivity(intent);
            }
        });

        checkit3.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        checkit3.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        checkit3.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}