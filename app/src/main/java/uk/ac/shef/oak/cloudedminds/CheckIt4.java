package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CheckIt4 extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_it4);
        final Vibrator vibe = (Vibrator) CheckIt4.this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView eventReceive = findViewById(R.id.eventReceive5);
        TextView dateReceive = findViewById(R.id.dateReceive5);
        TextView moodReceive = findViewById(R.id.moodReceive4);
        TextView ratingReceive = findViewById(R.id.ratingReceive4);
        TextView catasReceive = findViewById(R.id.catasReceive3);
        TextView geneReceive = findViewById(R.id.geneReceive2);
        TextView ignoreReceive = findViewById(R.id.ignoreReceive);
        RadioGroup criticise = findViewById(R.id.rdgSelfCritical);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(CheckIt4.this, MainActivity.class));
            }
        });

        TextView receiveEvent = findViewById(R.id.eventReceive5);
        TextView receiveDate = findViewById(R.id.dateReceive5);
        TextView receiveMood = findViewById(R.id.moodReceive4);
        TextView receiveRating = findViewById(R.id.ratingReceive4);
        TextView receiveCatas = findViewById(R.id.catasReceive3);
        TextView receiveGene = findViewById(R.id.geneReceive2);
        TextView receiveIgnore = findViewById(R.id.ignoreReceive);

        Intent intent = getIntent();
        String receivedEvent = intent.getStringExtra("event5_txt");
        String receivedDate = intent.getStringExtra("date5_txt");
        String receivedMood = intent.getStringExtra("mood4_txt");
        String receivedRating = intent.getStringExtra("rating4_txt");
        String receivedCatas = intent.getStringExtra("catastrophised3_txt");
        String receivedGene = intent.getStringExtra("generalised2_txt");
        String receivedIgnore = intent.getStringExtra("ignored_txt");

        receiveEvent.setText(receivedEvent);
        receiveDate.setText(receivedDate);
        receiveMood.setText(receivedMood);
        receiveRating.setText(receivedRating);
        receiveCatas.setText(receivedCatas);
        receiveGene.setText(receivedGene);
        receiveIgnore.setText(receivedIgnore);

        Button checkit5 = findViewById(R.id.btnToCheckIt5);
        checkit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                String txtEvent = eventReceive.getText().toString();
                String txtDate = dateReceive.getText().toString();
                String txtMood = moodReceive.getText().toString();
                String txtRating = ratingReceive.getText().toString();
                String txtCatas = catasReceive.getText().toString();
                String txtGene = geneReceive.getText().toString();
                String txtIgnore = ignoreReceive.getText().toString();
                int selectedId = criticise.getCheckedRadioButtonId();
                RadioButton self = findViewById(selectedId);
                String txtCritical = self.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CheckIt5.class);
                intent.putExtra("event6_txt", txtEvent);
                intent.putExtra("date6_txt", txtDate);
                intent.putExtra("mood5_txt", txtMood);
                intent.putExtra("rating5_txt", txtRating);
                intent.putExtra("catastrophised4_txt", txtCatas);
                intent.putExtra("generalised3_txt", txtGene);
                intent.putExtra("ignored2_txt", txtIgnore);
                intent.putExtra("critical_txt", txtCritical);
                startActivity(intent);
            }
        });

    }
}