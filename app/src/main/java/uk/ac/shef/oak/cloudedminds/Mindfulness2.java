package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindfulness2);
        final Vibrator vibe = (Vibrator) Mindfulness2.this.getSystemService(Context.VIBRATOR_SERVICE);

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
                startActivity(new Intent(Mindfulness2.this, Acceptance.class));
            }
        });
    }
}