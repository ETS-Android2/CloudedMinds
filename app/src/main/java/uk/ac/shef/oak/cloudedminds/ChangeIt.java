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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ChangeIt extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_it);
        final Vibrator vibe = (Vibrator) ChangeIt.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive7);
        TextView dateReceive = findViewById(R.id.dateReceive7);
        TextView moodReceive = findViewById(R.id.moodReceive6);
        TextView ratingReceive = findViewById(R.id.ratingReceive6);
        TextView catasReceive = findViewById(R.id.catasReceive5);
        TextView geneReceive = findViewById(R.id.geneReceive4);
        TextView ignoreReceive = findViewById(R.id.ignoreReceive3);
        TextView criticalReceive = findViewById(R.id.criticalReceive2);
        TextView mindReceive = findViewById(R.id.mindReceive);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(ChangeIt.this, MainActivity.class));
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive7);
        TextView receiveDate = findViewById(R.id.dateReceive7);
        TextView receiveMood = findViewById(R.id.moodReceive6);
        TextView receiveRating = findViewById(R.id.ratingReceive6);
        TextView receiveCatas = findViewById(R.id.catasReceive5);
        TextView receiveGene = findViewById(R.id.geneReceive4);
        TextView receiveIgnore = findViewById(R.id.ignoreReceive3);
        TextView receiveCritical = findViewById(R.id.criticalReceive2);
        TextView receiveMind = findViewById(R.id.mindReceive);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event7_txt");
        String receivedDate = intent.getStringExtra("date7_txt");
        String receivedMood = intent.getStringExtra("mood6_txt");
        String receivedRating = intent.getStringExtra("rating6_txt");
        String receivedCatas = intent.getStringExtra("catastrophised5_txt");
        String receivedGene = intent.getStringExtra("generalised4_txt");
        String receivedIgnore = intent.getStringExtra("ignored3_txt");
        String receivedCritical = intent.getStringExtra("critical2_txt");
        String receivedMind = intent.getStringExtra("mind_txt");

        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);
        receiveMood.setText(receivedMood);
        receiveRating.setText(receivedRating);
        receiveCatas.setText(receivedCatas);
        receiveGene.setText(receivedGene);
        receiveIgnore.setText(receivedIgnore);
        receiveCritical.setText(receivedCritical);
        receiveMind.setText(receivedMind);

        Button changeit2 = findViewById(R.id.btnToChangeIt2);
        changeit2.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(getApplicationContext(), ChangeIt2.class);
                intent.putExtra("event8_txt", txtEvent);
                intent.putExtra("date8_txt", txtDate);
                intent.putExtra("mood7_txt", txtMood);
                intent.putExtra("rating7_txt", txtRating);
                intent.putExtra("catastrophised6_txt", txtCatas);
                intent.putExtra("generalised5_txt", txtGene);
                intent.putExtra("ignored4_txt", txtIgnore);
                intent.putExtra("critical3_txt", txtCritical);
                intent.putExtra("mind2_txt", txtMind);
                startActivity(intent);
            }
        });

        changeit2.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        changeit2.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        changeit2.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}