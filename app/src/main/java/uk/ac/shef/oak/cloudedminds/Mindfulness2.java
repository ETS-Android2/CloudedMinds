package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Mindfulness2 extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindfulness2);
        final Vibrator vibe = (Vibrator) Mindfulness2.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive11);
        TextView dateReceive = findViewById(R.id.dateReceive11);
        TextView moodReceive = findViewById(R.id.moodReceive10);
        TextView ratingReceive = findViewById(R.id.ratingReceive10);
        TextView catasReceive = findViewById(R.id.catasReceive9);
        TextView geneReceive = findViewById(R.id.geneReceive8);
        TextView ignoreReceive = findViewById(R.id.ignoreReceive7);
        TextView criticalReceive = findViewById(R.id.criticalReceive6);
        TextView mindReceive = findViewById(R.id.mindReceive5);
        TextView changedMoodReceive = findViewById(R.id.changedmoodReceive3);
        TextView changedRateReceive = findViewById(R.id.changedrateReceive3);

        Button video = (Button)findViewById(R.id.btnVideo);
        video.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                // TODO Auto-generated method stub
                String url = "https://www.youtube.com/watch?app=desktop&v=wfDTp2GogaQ";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        video.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        video.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        video.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(Mindfulness2.this, MainActivity.class));
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive11);
        TextView receiveDate = findViewById(R.id.dateReceive11);
        TextView receiveMood = findViewById(R.id.moodReceive10);
        TextView receiveRating = findViewById(R.id.ratingReceive10);
        TextView receiveCatas = findViewById(R.id.catasReceive9);
        TextView receiveGene = findViewById(R.id.geneReceive8);
        TextView receiveIgnore = findViewById(R.id.ignoreReceive7);
        TextView receiveCritical = findViewById(R.id.criticalReceive6);
        TextView receiveMind = findViewById(R.id.mindReceive5);
        TextView receiveChangedMood = findViewById(R.id.changedmoodReceive3);
        TextView receiveChangedRate = findViewById(R.id.changedrateReceive3);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event11_txt");
        String receivedDate = intent.getStringExtra("date11_txt");
        String receivedMood = intent.getStringExtra("mood10_txt");
        String receivedRating = intent.getStringExtra("rating10_txt");
        String receivedCatas = intent.getStringExtra("catastrophised9_txt");
        String receivedGene = intent.getStringExtra("generalised8_txt");
        String receivedIgnore = intent.getStringExtra("ignored7_txt");
        String receivedCritical = intent.getStringExtra("critical6_txt");
        String receivedMind = intent.getStringExtra("mind5_txt");
        String receivedChangedMood = intent.getStringExtra("cmood3_txt");
        String receivedChangedRate = intent.getStringExtra("crating3_txt");

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

        Button acceptance = findViewById(R.id.btnToAccept);
        acceptance.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(getApplicationContext(), Acceptance.class);
                intent.putExtra("event12_txt", txtEvent);
                intent.putExtra("date12_txt", txtDate);
                intent.putExtra("mood11_txt", txtMood);
                intent.putExtra("rating11_txt", txtRating);
                intent.putExtra("catastrophised10_txt", txtCatas);
                intent.putExtra("generalised9_txt", txtGene);
                intent.putExtra("ignored8_txt", txtIgnore);
                intent.putExtra("critical7_txt", txtCritical);
                intent.putExtra("mind6_txt", txtMind);
                intent.putExtra("cmood4_txt", txtChangedMood);
                intent.putExtra("crating4_txt", txtChangedRating);
                startActivity(intent);
            }
        });

        acceptance.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        acceptance.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        acceptance.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}