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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.ac.shef.oak.cloudedminds.Retrofit.IMyService;
import uk.ac.shef.oak.cloudedminds.Session.SessionManager;

/**
 * This class implements the Diary screen which sends a GET request to the server and retrieves the user
 * entries to display the entries for the user logged in.
 *
 * Based on tutorial by Coding in Flow on Youtube: https://www.youtube.com/watch?v=4JGvDUlfk7Y
 */
public class Diary extends AppCompatActivity {

    private MediaPlayer mp;
    private TextView listEntries;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        final Vibrator vibe = (Vibrator) Diary.this.getSystemService(Context.VIBRATOR_SERVICE);

        // Retrieves the username of the logged in user
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String username = user.get(SessionManager.USERNAME);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(Diary.this, MainActivity.class));

            }
        });

        listEntries = findViewById(R.id.listEntries);
        // A new builder is created with Gson converter factory
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.0.18:3000/") // In emulator: .baseUrl("http://10.0.2.2:3000/") || In personal device: .baseUrl("http://192.168.0.18:3000/") 
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        IMyService iMyService = retrofit.create(IMyService.class);
        Call<List<Entry>> call = iMyService.getEntries();

        call.enqueue(new Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                List<Entry> entries = response.body();

                // Loops through each entry in the response and displays the entries while skipping any entry which is not made by the logged in user.
                // The user sees only the entries they have made.
                for (Entry entry : entries) {
                    if (!entry.getUser().equals(username)) {
                        continue;
                    }

                    String content = "";
                    content += "Event: " + entry.getEvent() + "\n";
                    content += "Date: " + entry.getDate() + "\n";
                    content += "Mood: " + entry.getMood() + "\n";
                    content += "Mood Rating: " + entry.getMood_rating() + "\n";
                    content += "Catastrophised: " + entry.getCatastrophise() + "\n";
                    content += "Generalised: " + entry.getGeneralise() + "\n";
                    content += "Ignored the Positive: " + entry.getIgnoring() + "\n";
                    content += "Self-Critical: " + entry.getSelf_critical() + "\n";
                    content += "Mind Read: " + entry.getMind_reading() + "\n";
                    content += "Changed Mood: " + entry.getChanged_mood() + "\n";
                    content += "Changed Rating: " + entry.getChanged_rating() + "\n\n\n";

                    listEntries.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {
                listEntries.setText(t.getMessage());
            }
        });
    }
}