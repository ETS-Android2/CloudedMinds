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
import android.widget.TextView;

public class CatchIt extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_it);
        final Vibrator vibe = (Vibrator) CatchIt.this.getSystemService(Context.VIBRATOR_SERVICE);
        EditText event = findViewById(R.id.txtEvent);
        EditText date = findViewById(R.id.txtDate);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(CatchIt.this, MainActivity.class));
            }
        });

        Button next = findViewById(R.id.btnNextCatchIt);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                String txtEvent = event.getText().toString();
                String txtDate = date.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CatchIt2.class);
                intent.putExtra("event_txt", txtEvent);
                intent.putExtra("date_txt", txtDate);
                startActivity(intent);
            }
        });
    }
}