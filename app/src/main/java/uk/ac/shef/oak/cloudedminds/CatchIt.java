package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.Calendar;

/**
 * This class allows the user to enter the event and date which are stored with Shared Preferences
 * so that it can be retrieved in the end screen for saving.
 */
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

        /* Button to return to home should the user wish to. A warning given in an alert box stating that
        any entered data will be removed.*/
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

        // Tapping on entry text for date opens the date picker in which the user can select the date.
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

        /* Button which checks if any entry fields are empty, if not, it stores the event and date in
            shared preferences and moves to the next screen. Displays error alert boxes if fields are empty.
         */
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
                else if(txtDate.equals("Tap to Enter Date")){
                    AlertDialog.Builder alert = new AlertDialog.Builder(CatchIt.this);
                    alert.setTitle("Empty Date");
                    alert.setMessage("Please enter the date of this event.");
                    alert.setPositiveButton("OK",null);
                    alert.show();
                }
                else {
                    SharedPreferences sharedPref = getSharedPreferences("ENTRIES", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("event", event.getText().toString());
                    editor.putString("date", dateText.getText().toString());
                    editor.apply();

                    startActivity(new Intent(CatchIt.this, CatchIt2.class));
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