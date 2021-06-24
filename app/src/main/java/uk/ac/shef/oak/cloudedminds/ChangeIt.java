package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChangeIt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_it);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeIt.this, MainActivity.class));
            }
        });

        Button changeit2 = findViewById(R.id.btnToChangeIt2);
        changeit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeIt.this, ChangeIt2.class));
            }
        });
    }
}