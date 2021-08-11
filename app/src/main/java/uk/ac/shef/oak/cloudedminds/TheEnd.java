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

/**
 * This class retrieves all the entered data of the user from the previous
 * screens and saves them in the database.
 *
 * Part of saving data based on tutorial by EMDT Dev on YouTube: https://www.youtube.com/watch?v=4Xq2FUJvE-c
 */
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

        // Initiates vibrator
        final Vibrator vibe = (Vibrator) TheEnd.this.getSystemService(Context.VIBRATOR_SERVICE);

        // Retrieves the username of the currently logged in user from session manager
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String username = user.get(SessionManager.USERNAME);

        // Initialises retrofit client
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
                alert.setTitle("Return to Main Menu");
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

        // Retrieves previously entered data through the use of Shared Preferences.
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

        // Button to save users entered mood details into the database
        Button end = findViewById(R.id.btnFinish);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                AlertDialog.Builder alert = new AlertDialog.Builder(TheEnd.this);
                alert.setTitle("Submit Entries");
                alert.setMessage("Your session entries will be saved and you will be taken back to the Main Menu. Are you sure you wish to do this?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dataEntry(username, event, date, mood,
                                rating, catastrophise, generalised,
                                ignored, critical, mind,
                                changedMood, changedRating);
                    }
                });
                alert.setNegativeButton("NO", null);
                alert.show();
            }
        });

    }

    /**
     * Posts the data entered by the user in the previous screens into the entries database.
     * Once the data is entered, the user is taken back to the main menu screen.
     *
     * @param user
     * @param event
     * @param date
     * @param mood
     * @param rating
     * @param catastrophise
     * @param generalise
     * @param ignore
     * @param critical
     * @param mind
     * @param changedMood
     * @param changedRate
     */
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