package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import uk.ac.shef.oak.cloudedminds.Retrofit.IMyService;
import uk.ac.shef.oak.cloudedminds.Retrofit.RetrofitClient;

public class TheEnd extends AppCompatActivity {

    private MediaPlayer mp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;

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

        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(TheEnd.this, MainActivity.class));
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

    }

    private void dataEntry(String event, String date, String mood, Integer rating, String catastrophise, String generalise, String ignore, String critical, String mind, String changedMood, String changedRate){
        compositeDisposable.add()
    }
}