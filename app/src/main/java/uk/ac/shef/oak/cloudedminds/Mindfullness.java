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
import android.widget.TextView;

public class Mindfullness extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindfullness);
        final Vibrator vibe = (Vibrator) Mindfullness.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive10);
        TextView dateReceive = findViewById(R.id.dateReceive10);
        TextView moodReceive = findViewById(R.id.moodReceive9);
        TextView ratingReceive = findViewById(R.id.ratingReceive9);
        TextView catasReceive = findViewById(R.id.catasReceive8);
        TextView geneReceive = findViewById(R.id.geneReceive7);
        TextView ignoreReceive = findViewById(R.id.ignoreReceive6);
        TextView criticalReceive = findViewById(R.id.criticalReceive5);
        TextView mindReceive = findViewById(R.id.mindReceive4);
        TextView changedMoodReceive = findViewById(R.id.changedmoodReceive2);
        TextView changedRateReceive = findViewById(R.id.changedrateReceive2);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(Mindfullness.this, MainActivity.class));
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive10);
        TextView receiveDate = findViewById(R.id.dateReceive10);
        TextView receiveMood = findViewById(R.id.moodReceive9);
        TextView receiveRating = findViewById(R.id.ratingReceive9);
        TextView receiveCatas = findViewById(R.id.catasReceive8);
        TextView receiveGene = findViewById(R.id.geneReceive7);
        TextView receiveIgnore = findViewById(R.id.ignoreReceive6);
        TextView receiveCritical = findViewById(R.id.criticalReceive5);
        TextView receiveMind = findViewById(R.id.mindReceive4);
        TextView receiveChangedMood = findViewById(R.id.changedmoodReceive2);
        TextView receiveChangedRate = findViewById(R.id.changedrateReceive2);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event10_txt");
        String receivedDate = intent.getStringExtra("date10_txt");
        String receivedMood = intent.getStringExtra("mood9_txt");
        String receivedRating = intent.getStringExtra("rating9_txt");
        String receivedCatas = intent.getStringExtra("catastrophised8_txt");
        String receivedGene = intent.getStringExtra("generalised7_txt");
        String receivedIgnore = intent.getStringExtra("ignored6_txt");
        String receivedCritical = intent.getStringExtra("critical5_txt");
        String receivedMind = intent.getStringExtra("mind4_txt");
        String receivedChangedMood = intent.getStringExtra("cmood2_txt");
        String receivedChangedRate = intent.getStringExtra("crating2_txt");

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

        Button mind2 = findViewById(R.id.btnToMind2);
        mind2.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(getApplicationContext(), Mindfulness2.class);
                intent.putExtra("event11_txt", txtEvent);
                intent.putExtra("date11_txt", txtDate);
                intent.putExtra("mood10_txt", txtMood);
                intent.putExtra("rating10_txt", txtRating);
                intent.putExtra("catastrophised9_txt", txtCatas);
                intent.putExtra("generalised8_txt", txtGene);
                intent.putExtra("ignored7_txt", txtIgnore);
                intent.putExtra("critical6_txt", txtCritical);
                intent.putExtra("mind5_txt", txtMind);
                intent.putExtra("cmood3_txt", txtChangedMood);
                intent.putExtra("crating3_txt", txtChangedRating);
                startActivity(intent);
            }
        });
    }
}