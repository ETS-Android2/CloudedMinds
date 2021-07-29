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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Rumination extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rumination);
        final Vibrator vibe = (Vibrator) Rumination.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive9);
        TextView dateReceive = findViewById(R.id.dateReceive9);
        TextView moodReceive = findViewById(R.id.moodReceive8);
        TextView ratingReceive = findViewById(R.id.ratingReceive8);
        TextView catasReceive = findViewById(R.id.catasReceive7);
        TextView geneReceive = findViewById(R.id.geneReceive6);
        TextView ignoreReceive = findViewById(R.id.ignoreReceive5);
        TextView criticalReceive = findViewById(R.id.criticalReceive4);
        TextView mindReceive = findViewById(R.id.mindReceive3);
        TextView changedMoodReceive = findViewById(R.id.changedmoodReceive);
        TextView changedRateReceive = findViewById(R.id.changedrateReceive);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                AlertDialog.Builder alert = new AlertDialog.Builder(Rumination.this);
                alert.setTitle("Are you sure?");
                alert.setMessage("You will return to the Main Menu which will cause all your entered data to be removed. Are you sure you wish to do this?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Rumination.this, MainActivity.class));
                    }
                });
                alert.setNegativeButton("NO", null);
                alert.show();
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive9);
        TextView receiveDate = findViewById(R.id.dateReceive9);
        TextView receiveMood = findViewById(R.id.moodReceive8);
        TextView receiveRating = findViewById(R.id.ratingReceive8);
        TextView receiveCatas = findViewById(R.id.catasReceive7);
        TextView receiveGene = findViewById(R.id.geneReceive6);
        TextView receiveIgnore = findViewById(R.id.ignoreReceive5);
        TextView receiveCritical = findViewById(R.id.criticalReceive4);
        TextView receiveMind = findViewById(R.id.mindReceive3);
        TextView receiveChangedMood = findViewById(R.id.changedmoodReceive);
        TextView receiveChangedRate = findViewById(R.id.changedrateReceive);
        TextView receiveUser = findViewById(R.id.receiveUser14);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event9_txt");
        String receivedDate = intent.getStringExtra("date9_txt");
        String receivedMood = intent.getStringExtra("mood8_txt");
        String receivedRating = intent.getStringExtra("rating8_txt");
        String receivedCatas = intent.getStringExtra("catastrophised7_txt");
        String receivedGene = intent.getStringExtra("generalised6_txt");
        String receivedIgnore = intent.getStringExtra("ignored5_txt");
        String receivedCritical = intent.getStringExtra("critical4_txt");
        String receivedMind = intent.getStringExtra("mind3_txt");
        String receivedChangedMood = intent.getStringExtra("cmood_txt");
        String receivedChangedRate = intent.getStringExtra("crating_txt");
        String receivedUser = intent.getStringExtra("username12");

        receiveUser.setText(receivedUser);
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


        Button mind = findViewById(R.id.btnToMind);
        mind.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(getApplicationContext(), Mindfullness.class);
                intent.putExtra("event10_txt", txtEvent);
                intent.putExtra("date10_txt", txtDate);
                intent.putExtra("mood9_txt", txtMood);
                intent.putExtra("rating9_txt", txtRating);
                intent.putExtra("catastrophised8_txt", txtCatas);
                intent.putExtra("generalised7_txt", txtGene);
                intent.putExtra("ignored6_txt", txtIgnore);
                intent.putExtra("critical5_txt", txtCritical);
                intent.putExtra("mind4_txt", txtMind);
                intent.putExtra("cmood2_txt", txtChangedMood);
                intent.putExtra("crating2_txt", txtChangedRating);
                intent.putExtra("username15", receiveUser.getText().toString());
                startActivity(intent);
            }
        });

        mind.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        mind.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        mind.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}