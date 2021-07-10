package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
                
                // Checks if fields are empty and allows user to move ahead when they've entered the details.
                if(txtEvent.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(CatchIt.this);
                    alert.setTitle("Empty Event");
                    alert.setMessage("Please enter the event which made you feel strong emotions.");
                    alert.setPositiveButton("OK",null);
                    alert.show();
                }
                else if(txtDate.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(CatchIt.this);
                    alert.setTitle("Empty Date");
                    alert.setMessage("Please enter the date of this event.");
                    alert.setPositiveButton("OK",null);
                    alert.show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), CatchIt2.class);
                    intent.putExtra("event_txt", txtEvent);
                    intent.putExtra("date_txt", txtDate);
                    startActivity(intent);
                }
            }
        });
    }
}