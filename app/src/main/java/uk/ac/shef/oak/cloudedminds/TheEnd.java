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

        TextView receiveEvent = findViewById(R.id.eventReceive13);
        TextView receiveDate = findViewById(R.id.dateReceive13);
        TextView receiveMood = findViewById(R.id.moodReceive12);
        TextView receiveRating = findViewById(R.id.ratingReceive12);
        TextView receiveCatas = findViewById(R.id.catasReceive11);
        TextView receiveGene = findViewById(R.id.geneReceive10);
        TextView receiveIgnore = findViewById(R.id.ignoreReceive9);
        TextView receiveCritical = findViewById(R.id.criticalReceive8);
        TextView receiveMind = findViewById(R.id.mindReceive7);
        TextView receiveChangedMood = findViewById(R.id.changedmoodReceive5);
        TextView receiveChangedRate = findViewById(R.id.changedrateReceive5);
        TextView receiveUser = findViewById(R.id.receiveUser16);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event13_txt");
        String receivedDate = intent.getStringExtra("date13_txt");
        String receivedMood = intent.getStringExtra("mood12_txt");
        String receivedRating = intent.getStringExtra("rating12_txt");
        String receivedCatas = intent.getStringExtra("catastrophised11_txt");
        String receivedGene = intent.getStringExtra("generalised10_txt");
        String receivedIgnore = intent.getStringExtra("ignored9_txt");
        String receivedCritical = intent.getStringExtra("critical8_txt");
        String receivedMind = intent.getStringExtra("mind7_txt");
        String receivedChangedMood = intent.getStringExtra("cmood5_txt");
        String receivedChangedRate = intent.getStringExtra("crating5_txt");
        String receivedUser = intent.getStringExtra("username16");

        receiveUser.setText(receivedUser);
        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);
        receiveMood.setText(receivedMood);
        receiveRating.setText(receivedRating);
        receiveCatas.setText(receivedCatas);
        receiveGene.setText(receivedGene);
        receiveIgnore.setText(receivedIgnore);
        receiveCritical.setText(receivedCritical);
        receiveMind.setText(receivedMind);
        receiveChangedMood.setText(receivedChangedMood);
        receiveChangedRate.setText(receivedChangedRate);



        Button end = findViewById(R.id.btnFinish);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                dataEntry(username, receiveEvent.getText().toString(), receiveDate.getText().toString(), receiveMood.getText().toString(),
                        Integer.parseInt(receiveRating.getText().toString()), receiveCatas.getText().toString(), receiveGene.getText().toString(),
                        receiveIgnore.getText().toString(), receiveCritical.getText().toString(), receiveMind.getText().toString(),
                        receiveChangedMood.getText().toString(), Integer.parseInt(receiveChangedRate.getText().toString()));

                startActivity(new Intent(TheEnd.this, MainActivity.class));
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
                Log.i("myTag", "Data sent to database.");
            }
        }));
    }
}