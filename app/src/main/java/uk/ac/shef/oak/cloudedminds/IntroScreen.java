package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * This class implements the introduction screen which sets the scene for the mood entry session.
 */
public class IntroScreen extends AppCompatActivity {

    TextView hello;
    Button helloSpk;

    TextToSpeech mTTS;

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        hello = findViewById(R.id.speech);
        helloSpk = findViewById(R.id.btnHelloSpk);
        final Vibrator vibe = (Vibrator) IntroScreen.this.getSystemService(Context.VIBRATOR_SERVICE);

        mTTS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int lang = mTTS.setLanguage(Locale.UK);

                    if(lang == TextToSpeech.LANG_MISSING_DATA || lang == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(IntroScreen.this, "Language not supported", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        helloSpk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String toSpeak = hello.getText().toString();
                if(toSpeak.equals("")){
                    Toast.makeText(IntroScreen.this, "No Text", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Speak the text
                    mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(IntroScreen.this, MainActivity.class));
            }
        });

        Button start = findViewById(R.id.btnStartSession);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                mTTS.stop();
                mTTS.shutdown();
                startActivity(new Intent(IntroScreen.this, CatchIt.class));
            }
        });

        start.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        start.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        start.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }
    };
}