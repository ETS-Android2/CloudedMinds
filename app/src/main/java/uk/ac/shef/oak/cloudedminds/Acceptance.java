package uk.ac.shef.oak.cloudedminds;

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
import android.widget.ImageView;
import android.widget.TextView;

public class Acceptance extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptance);
        final Vibrator vibe = (Vibrator) Acceptance.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive12);
        TextView dateReceive = findViewById(R.id.dateReceive12);
        TextView moodReceive = findViewById(R.id.moodReceive11);
        TextView ratingReceive = findViewById(R.id.ratingReceive11);
        TextView catasReceive = findViewById(R.id.catasReceive10);
        TextView geneReceive = findViewById(R.id.geneReceive9);
        TextView ignoreReceive = findViewById(R.id.ignoreReceive8);
        TextView criticalReceive = findViewById(R.id.criticalReceive7);
        TextView mindReceive = findViewById(R.id.mindReceive6);
        TextView changedMoodReceive = findViewById(R.id.changedmoodReceive4);
        TextView changedRateReceive = findViewById(R.id.changedrateReceive4);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(Acceptance.this, MainActivity.class));
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive12);
        TextView receiveDate = findViewById(R.id.dateReceive12);
        TextView receiveMood = findViewById(R.id.moodReceive11);
        TextView receiveRating = findViewById(R.id.ratingReceive11);
        TextView receiveCatas = findViewById(R.id.catasReceive10);
        TextView receiveGene = findViewById(R.id.geneReceive9);
        TextView receiveIgnore = findViewById(R.id.ignoreReceive8);
        TextView receiveCritical = findViewById(R.id.criticalReceive7);
        TextView receiveMind = findViewById(R.id.mindReceive6);
        TextView receiveChangedMood = findViewById(R.id.changedmoodReceive4);
        TextView receiveChangedRate = findViewById(R.id.changedrateReceive4);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event12_txt");
        String receivedDate = intent.getStringExtra("date12_txt");
        String receivedMood = intent.getStringExtra("mood11_txt");
        String receivedRating = intent.getStringExtra("rating11_txt");
        String receivedCatas = intent.getStringExtra("catastrophised10_txt");
        String receivedGene = intent.getStringExtra("generalised9_txt");
        String receivedIgnore = intent.getStringExtra("ignored8_txt");
        String receivedCritical = intent.getStringExtra("critical7_txt");
        String receivedMind = intent.getStringExtra("mind6_txt");
        String receivedChangedMood = intent.getStringExtra("cmood4_txt");
        String receivedChangedRate = intent.getStringExtra("crating4_txt");

        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);
        receiveMood.setText(receivedMood);
        receiveRating.setText(receivedRating);
        receiveCatas.setText(receivedCatas);
        receiveGene.setText(receivedGene);
        receiveIgnore.setText(receivedIgnore);
        receiveCritical.setText(receivedCritical);
        receiveMind.setText(receivedMind);
        receiveChangedMood.setText(receivedChangedMood);
        receiveChangedRate.setText(receivedChangedRate);

        Button end = findViewById(R.id.btnToEnd);
        end.setOnClickListener(new View.OnClickListener() {
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
                String txtGene = geneReceive.getText().toString();
                String txtIgnore = ignoreReceive.getText().toString();
                String txtCritical = criticalReceive.getText().toString();
                String txtMind = mindReceive.getText().toString();
                String txtChangedMood = changedMoodReceive.getText().toString();
                String txtChangedRating = changedRateReceive.getText().toString();
                Intent intent = new Intent(getApplicationContext(), TheEnd.class);
                intent.putExtra("event13_txt", txtEvent);
                intent.putExtra("date13_txt", txtDate);
                intent.putExtra("mood12_txt", txtMood);
                intent.putExtra("rating12_txt", txtRating);
                intent.putExtra("catastrophised11_txt", txtCatas);
                intent.putExtra("generalised10_txt", txtGene);
                intent.putExtra("ignored9_txt", txtIgnore);
                intent.putExtra("critical8_txt", txtCritical);
                intent.putExtra("mind7_txt", txtMind);
                intent.putExtra("cmood5_txt", txtChangedMood);
                intent.putExtra("crating5_txt", txtChangedRating);
                startActivity(intent);
            }
        });

        end.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        end.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        end.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}