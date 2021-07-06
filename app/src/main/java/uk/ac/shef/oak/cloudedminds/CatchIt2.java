package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CatchIt2 extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_it2);
        final Vibrator vibe = (Vibrator) CatchIt2.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.txtEventReceive);
        TextView dateReceive = findViewById(R.id.txtDateReceive);
        EditText mood = findViewById(R.id.txtMood);
        RadioGroup moodrating = findViewById(R.id.rdGroupMood);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(CatchIt2.this, MainActivity.class));
            }
        });

        TextView receiveEvent = findViewById(R.id.txtEventReceive);
        TextView receiveDate = findViewById(R.id.txtDateReceive);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event_txt");
        String receivedDate = intent.getStringExtra("date_txt");
        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);

        Button checkit = findViewById(R.id.btnToCheckIt);
        checkit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                String txtEventReceive = eventReceive.getText().toString();
                String txtDateReceive = dateReceive.getText().toString();
                String txtMood = mood.getText().toString();
                int selectedId = moodrating.getCheckedRadioButtonId();
                RadioButton moodRate = findViewById(selectedId);
                String txtRating = moodRate.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CheckIt.class);
                intent.putExtra("event2_txt", txtEventReceive);
                intent.putExtra("date2_txt", txtDateReceive);
                intent.putExtra("mood_txt", txtMood);
                intent.putExtra("rating_txt", txtRating);
                startActivity(intent);
            }
        });
    }
}