package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CatchIt extends AppCompatActivity {

    private MediaPlayer mp;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_it);
        final Vibrator vibe = (Vibrator) CatchIt.this.getSystemService(Context.VIBRATOR_SERVICE);
        EditText event = findViewById(R.id.txtEvent);
        TextView dateText = findViewById(R.id.txtDate);
        TextView receiveUser = findViewById(R.id.receiveUser3);

        Intent intent = getIntent();
        String receivedUser = intent.getStringExtra("username3");
        receiveUser.setText(receivedUser);


        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);

                AlertDialog.Builder alert = new AlertDialog.Builder(CatchIt.this);
                alert.setTitle("Are you sure?");
                alert.setMessage("You will return to the Main Menu which will cause all your entered data to be removed. Are you sure you wish to do this?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(CatchIt.this, MainActivity.class));
                    }
                });
                alert.setNegativeButton("NO", null);
                alert.show();
            }
        });

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        CatchIt.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month + 1;
                Log.d("TAG", "onDateSet : dd/mm/yyyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                dateText.setText(date);
            }
        };

        Button next = findViewById(R.id.btnNextCatchIt);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                String txtEvent = event.getText().toString();
                String txtDate = dateText.getText().toString();
                
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
                    intent.putExtra("username4", receiveUser.getText().toString());
                    startActivity(intent);
                }
            }
        });

        next.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        next.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        next.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}