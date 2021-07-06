package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CheckIt extends AppCompatActivity {

    private MediaPlayer mp;

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
                startActivity(new Intent(CheckIt.this, MainActivity.class));
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive2);
        TextView receiveDate = findViewById(R.id.dateReceive2);
        TextView receiveMood = findViewById(R.id.moodReceive);
        TextView receiveRating = findViewById(R.id.ratingReceive);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event2_txt");
        String receivedDate = intent.getStringExtra("date2_txt");
        String receivedMood = intent.getStringExtra("mood_txt");
        String receivedRating = intent.getStringExtra("rating_txt");

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
                startActivity(intent);
            }
        });

    }
}