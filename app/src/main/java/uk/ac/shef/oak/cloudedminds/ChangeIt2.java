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

public class ChangeIt2 extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_it2);
        final Vibrator vibe = (Vibrator) ChangeIt2.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive8);
        TextView dateReceive = findViewById(R.id.dateReceive8);
        TextView moodReceive = findViewById(R.id.moodReceive7);
        TextView ratingReceive = findViewById(R.id.ratingReceive7);
        TextView catasReceive = findViewById(R.id.catasReceive6);
        TextView geneReceive = findViewById(R.id.geneReceive5);
        TextView ignoreReceive = findViewById(R.id.ignoreReceive4);
        TextView criticalReceive = findViewById(R.id.criticalReceive3);
        TextView mindReceive = findViewById(R.id.mindReceive2);
        EditText changedMood = findViewById(R.id.txtChangedMood);
        RadioGroup changedRating = findViewById(R.id.rdgChangedRating);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                AlertDialog.Builder alert = new AlertDialog.Builder(ChangeIt2.this);
                alert.setTitle("Are you sure?");
                alert.setMessage("You will return to the Main Menu which will cause all your entered data to be removed. Are you sure you wish to do this?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(ChangeIt2.this, MainActivity.class));
                    }
                });
                alert.setNegativeButton("NO", null);
                alert.show();
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive8);
        TextView receiveDate = findViewById(R.id.dateReceive8);
        TextView receiveMood = findViewById(R.id.moodReceive7);
        TextView receiveRating = findViewById(R.id.ratingReceive7);
        TextView receiveCatas = findViewById(R.id.catasReceive6);
        TextView receiveGene = findViewById(R.id.geneReceive5);
        TextView receiveIgnore = findViewById(R.id.ignoreReceive4);
        TextView receiveCritical = findViewById(R.id.criticalReceive3);
        TextView receiveMind = findViewById(R.id.mindReceive2);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event8_txt");
        String receivedDate = intent.getStringExtra("date8_txt");
        String receivedMood = intent.getStringExtra("mood7_txt");
        String receivedRating = intent.getStringExtra("rating7_txt");
        String receivedCatas = intent.getStringExtra("catastrophised6_txt");
        String receivedGene = intent.getStringExtra("generalised5_txt");
        String receivedIgnore = intent.getStringExtra("ignored4_txt");
        String receivedCritical = intent.getStringExtra("critical3_txt");
        String receivedMind = intent.getStringExtra("mind2_txt");

        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);
        receiveMood.setText(receivedMood);
        receiveRating.setText(receivedRating);
        receiveCatas.setText(receivedCatas);
        receiveGene.setText(receivedGene);
        receiveIgnore.setText(receivedIgnore);
        receiveCritical.setText(receivedCritical);
        receiveMind.setText(receivedMind);

        Button rumination = findViewById(R.id.btnToRumination);
        rumination.setOnClickListener(new View.OnClickListener() {
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
                String txtChangedMood = changedMood.getText().toString();
                int selectedId = changedRating.getCheckedRadioButtonId();
                RadioButton difRate = findViewById(selectedId);
                String txtChangedRating = difRate.getText().toString();

                // Checks if fields are empty and allows user to move ahead when they've entered the details.
                if(txtChangedMood.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(ChangeIt2.this);
                    alert.setTitle("Empty Mood");
                    alert.setMessage("Please enter your changed mood.");
                    alert.setPositiveButton("OK",null);
                    alert.show();
                }
                else if(txtChangedRating.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(ChangeIt2.this);
                    alert.setTitle("Empty Rating");
                    alert.setMessage("Please rate the current strength of your mood.");
                    alert.setPositiveButton("OK",null);
                    alert.show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), Rumination.class);
                    intent.putExtra("event9_txt", txtEvent);
                    intent.putExtra("date9_txt", txtDate);
                    intent.putExtra("mood8_txt", txtMood);
                    intent.putExtra("rating8_txt", txtRating);
                    intent.putExtra("catastrophised7_txt", txtCatas);
                    intent.putExtra("generalised6_txt", txtGene);
                    intent.putExtra("ignored5_txt", txtIgnore);
                    intent.putExtra("critical4_txt", txtCritical);
                    intent.putExtra("mind3_txt", txtMind);
                    intent.putExtra("cmood_txt", txtChangedMood);
                    intent.putExtra("crating_txt", txtChangedRating);
                    startActivity(intent);
                }
            }
        });

        rumination.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        rumination.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        rumination.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}