package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import uk.ac.shef.oak.cloudedminds.Retrofit.IMyService;
import uk.ac.shef.oak.cloudedminds.Retrofit.RetrofitClient;

public class SignUp extends AppCompatActivity {

    private MediaPlayer mp;

    MaterialEditText editName, editUsername, editPassword;
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
        setContentView(R.layout.activity_sign_up);

        final Vibrator vibe = (Vibrator) SignUp.this.getSystemService(Context.VIBRATOR_SERVICE);

        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);

        editName = (MaterialEditText)findViewById(R.id.editName);
        editUsername = (MaterialEditText)findViewById(R.id.editUsername2);
        editPassword = (MaterialEditText)findViewById(R.id.editPassword2);

        Button signup = findViewById(R.id.btnSignUp2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                signupUser(editName.getText().toString(), editUsername.getText().toString(),
                        editPassword.getText().toString());
            }
        });

    }


    private void signupUser(String name, String username, String password) {
        if(TextUtils.isEmpty(name)){
            AlertDialog.Builder alert = new AlertDialog.Builder(SignUp.this);
            alert.setTitle("Empty Name");
            alert.setMessage("Please enter your full name");
            alert.setNegativeButton("OK", null);
            alert.show();
            return;
        }

        if(TextUtils.isEmpty(username)){
            AlertDialog.Builder alert = new AlertDialog.Builder(SignUp.this);
            alert.setTitle("Empty Username");
            alert.setMessage("Please enter your username");
            alert.setNegativeButton("OK", null);
            alert.show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            AlertDialog.Builder alert = new AlertDialog.Builder(SignUp.this);
            alert.setTitle("Empty Password");
            alert.setMessage("Please enter your password");
            alert.setNegativeButton("OK", null);
            alert.show();
            return;
        }

        compositeDisposable.add(iMyService.signupUser(name,username,password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String response) throws Exception {
                if(response.equals("\"Username already exists\"")) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(SignUp.this);
                    alert.setTitle("Username Already Exists");
                    alert.setMessage("The username you are trying to create already exists. Please enter a different username.");
                    alert.setNegativeButton("OK", null);
                    alert.show();
                }
                else {
                    startActivity(new Intent(SignUp.this, Login.class));
                }
            }
        }));
    }
}