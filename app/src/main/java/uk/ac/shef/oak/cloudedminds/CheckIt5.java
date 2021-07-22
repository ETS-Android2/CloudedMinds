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

public class CheckIt5 extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_it5);
        final Vibrator vibe = (Vibrator) CheckIt5.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive6);
        TextView dateReceive = findViewById(R.id.dateReceive6);
        TextView moodReceive = findViewById(R.id.moodReceive5);
        TextView ratingReceive = findViewById(R.id.ratingReceive5);
        TextView catasReceive = findViewById(R.id.catasReceive4);
        TextView geneReceive = findViewById(R.id.geneReceive3);
        TextView ignoreReceive = findViewById(R.id.ignoreReceive2);
        TextView criticalReceive = findViewById(R.id.criticalReceive);
        RadioGroup mindread = findViewById(R.id.rdgMindReading);


        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(CheckIt5.this, MainActivity.class));
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive6);
        TextView receiveDate = findViewById(R.id.dateReceive6);
        TextView receiveMood = findViewById(R.id.moodReceive5);
        TextView receiveRating = findViewById(R.id.ratingReceive5);
        TextView receiveCatas = findViewById(R.id.catasReceive4);
        TextView receiveGene = findViewById(R.id.geneReceive3);
        TextView receiveIgnore = findViewById(R.id.ignoreReceive2);
        TextView receiveCritical = findViewById(R.id.criticalReceive);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event6_txt");
        String receivedDate = intent.getStringExtra("date6_txt");
        String receivedMood = intent.getStringExtra("mood5_txt");
        String receivedRating = intent.getStringExtra("rating5_txt");
        String receivedCatas = intent.getStringExtra("catastrophised4_txt");
        String receivedGene = intent.getStringExtra("generalised3_txt");
        String receivedIgnore = intent.getStringExtra("ignored2_txt");
        String receivedCritical = intent.getStringExtra("critical_txt");

        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);
        receiveMood.setText(receivedMood);
        receiveRating.setText(receivedRating);
        receiveCatas.setText(receivedCatas);
        receiveGene.setText(receivedGene);
        receiveIgnore.setText(receivedIgnore);
        receiveCritical.setText(receivedCritical);

        Button changeit = findViewById(R.id.btnToChangeIt);
        changeit.setOnClickListener(new View.OnClickListener() {
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
                int selectedId = mindread.getCheckedRadioButtonId();
                RadioButton mind = findViewById(selectedId);
                String txtMind = mind.getText().toString();
                Intent intent = new Intent(getApplicationContext(), ChangeIt.class);
                intent.putExtra("event7_txt", txtEvent);
                intent.putExtra("date7_txt", txtDate);
                intent.putExtra("mood6_txt", txtMood);
                intent.putExtra("rating6_txt", txtRating);
                intent.putExtra("catastrophised5_txt", txtCatas);
                intent.putExtra("generalised4_txt", txtGene);
                intent.putExtra("ignored3_txt", txtIgnore);
                intent.putExtra("critical2_txt", txtCritical);
                intent.putExtra("mind_txt", txtMind);
                startActivity(intent);
            }
        });

        changeit.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        changeit.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        changeit.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}