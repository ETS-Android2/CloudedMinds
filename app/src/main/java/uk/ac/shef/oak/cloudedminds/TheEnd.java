package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import uk.ac.shef.oak.cloudedminds.Retrofit.IMyService;
import uk.ac.shef.oak.cloudedminds.Retrofit.RetrofitClient;
import uk.ac.shef.oak.cloudedminds.Session.SessionManager;

public class TheEnd extends AppCompatActivity {

    private MediaPlayer mp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;

    SessionManager sessionManager;


    @Override
    protected void onStop(){
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_end);
        final Vibrator vibe = (Vibrator) TheEnd.this.getSystemService(Context.VIBRATOR_SERVICE);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String username = user.get(SessionManager.USERNAME);

        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                AlertDialog.Builder alert = new AlertDialog.Builder(TheEnd.this);
                alert.setTitle("Are you sure?");
                alert.setMessage("You will return to the Main Menu which will cause all your entered data to be removed. Are you sure you wish to do this?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(TheEnd.this, MainActivity.class));
                    }
                });
                alert.setNegativeButton("NO", null);
                alert.show();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("ENTRIES", MODE_PRIVATE);
        String event = sharedPreferences.getString("event","");
        String date = sharedPreferences.getString("date", "");
        String mood = sharedPreferences.getString("mood","");
        Integer rating = Integer.parseInt(sharedPreferences.getString("rating", ""));
        String catastrophise = sharedPreferences.getString("catastrophised","");
        String generalised = sharedPreferences.getString("generalised", "");
        String ignored = sharedPreferences.getString("ignored","");
        String critical = sharedPreferences.getString("critical", "");
        String mind = sharedPreferences.getString("mind","");
        String changedMood = sharedPreferences.getString("changedmood", "");
        Integer changedRating = Integer.parseInt(sharedPreferences.getString("changedrating",""));


        Button end = findViewById(R.id.btnFinish);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                dataEntry(username, event, date, mood,
                        rating, catastrophise, generalised,
                        ignored, critical, mind,
                        changedMood, changedRating);
            }
        });

    }

    private void dataEntry(String user, String event, String date, String mood, Integer rating, String catastrophise, String generalise, String ignore, String critical, String mind, String changedMood, Integer changedRate){
        compositeDisposable.add(iMyService.enterData(user, event, date, mood, rating, catastrophise, generalise, ignore, critical, mind, changedMood, changedRate)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String response) throws Exception {
                startActivity(new Intent(TheEnd.this, MainActivity.class));
            }
        }));
    }
}