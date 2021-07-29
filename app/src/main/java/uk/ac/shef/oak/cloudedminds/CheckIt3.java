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

public class CheckIt3 extends AppCompatActivity {

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_it3);
        final Vibrator vibe = (Vibrator) CheckIt3.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive4);
        TextView dateReceive = findViewById(R.id.dateReceive4);
        TextView moodReceive = findViewById(R.id.moodReceive3);
        TextView ratingReceive = findViewById(R.id.ratingReceive3);
        TextView catasReceive = findViewById(R.id.catasReceive2);
        TextView geneReceive = findViewById(R.id.geneReceive);
        RadioGroup ignoring = findViewById(R.id.rdgIgnorePositive);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                AlertDialog.Builder alert = new AlertDialog.Builder(CheckIt3.this);
                alert.setTitle("Are you sure?");
                alert.setMessage("You will return to the Main Menu which will cause all your entered data to be removed. Are you sure you wish to do this?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(CheckIt3.this, MainActivity.class));
                    }
                });
                alert.setNegativeButton("NO", null);
                alert.show();
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive4);
        TextView receiveDate = findViewById(R.id.dateReceive4);
        TextView receiveMood = findViewById(R.id.moodReceive3);
        TextView receiveRating = findViewById(R.id.ratingReceive3);
        TextView receiveCatas = findViewById(R.id.catasReceive2);
        TextView receiveGene = findViewById(R.id.geneReceive);
        TextView receiveUser = findViewById(R.id.receiveUser7);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event4_txt");
        String receivedDate = intent.getStringExtra("date4_txt");
        String receivedMood = intent.getStringExtra("mood3_txt");
        String receivedRating = intent.getStringExtra("rating3_txt");
        String receivedCatas = intent.getStringExtra("catastrophised2_txt");
        String receivedGene = intent.getStringExtra("generalised_txt");
        String receivedUser = intent.getStringExtra("username7");

        receiveUser.setText(receivedUser);
        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);
        receiveMood.setText(receivedMood);
        receiveRating.setText(receivedRating);
        receiveCatas.setText(receivedCatas);
        receiveGene.setText(receivedGene);

        Button checkit4 = findViewById(R.id.btnToCheckIt4);
        checkit4.setOnClickListener(new View.OnClickListener() {
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
                int selectedId = ignoring.getCheckedRadioButtonId();
                RadioButton ignore = findViewById(selectedId);
                String txtIgnore = ignore.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CheckIt4.class);
                intent.putExtra("event5_txt", txtEvent);
                intent.putExtra("date5_txt", txtDate);
                intent.putExtra("mood4_txt", txtMood);
                intent.putExtra("rating4_txt", txtRating);
                intent.putExtra("catastrophised3_txt", txtCatas);
                intent.putExtra("generalised2_txt", txtGene);
                intent.putExtra("ignored_txt", txtIgnore);
                intent.putExtra("username8", receiveUser.getText().toString());
                startActivity(intent);
            }
        });

        checkit4.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        checkit4.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        checkit4.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}