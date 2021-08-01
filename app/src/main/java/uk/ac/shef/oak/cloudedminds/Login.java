package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.rengwuxian.materialedittext.MaterialEditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import uk.ac.shef.oak.cloudedminds.Retrofit.IMyService;
import uk.ac.shef.oak.cloudedminds.Retrofit.RetrofitClient;
import uk.ac.shef.oak.cloudedminds.Session.SessionManager;

public class Login extends AppCompatActivity {

    private MediaPlayer mp;

    MaterialEditText editUsername, editPassword;
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
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(getApplicationContext());

        final Vibrator vibe = (Vibrator) Login.this.getSystemService(Context.VIBRATOR_SERVICE);

        Button signup = findViewById(R.id.btnSignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });

        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);

        editUsername = (MaterialEditText)findViewById(R.id.editUsername);
        editPassword = (MaterialEditText)findViewById(R.id.editPassword);

        Button login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                loginUser(editUsername.getText().toString(),
                        editPassword.getText().toString());
            }
        });
    }

    private void loginUser(String username, String password) {
        if(TextUtils.isEmpty(username)){
            AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
            alert.setTitle("Empty Username");
            alert.setMessage("Please enter your username");
            alert.setNegativeButton("OK", null);
            alert.show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
            alert.setTitle("Empty Password");
            alert.setMessage("Please enter your password");
            alert.setNegativeButton("OK", null);
            alert.show();
            return;
        }

         compositeDisposable.add(iMyService.loginUser(username,password)
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe(new Consumer<String>() {
             @Override
             public void accept(String response) throws Exception {
                 if(response.equals("\"Username does not exist\"")) {
                     AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
                     alert.setTitle("Username Invalid");
                     alert.setMessage("The username you entered does not exist. Please enter a correct username.");
                     alert.setNegativeButton("OK", null);
                     alert.show();
                 }
                 else if(response.equals("\"Password is incorrect\"")){
                     AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
                     alert.setTitle("Password Incorrect");
                     alert.setMessage("The password you entered is incorrect. Please enter a correct password.");
                     alert.setNegativeButton("OK", null);
                     alert.show();
                 }
                 else {
                     Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                     intent.putExtra("username", editUsername.getText().toString());
                     startActivity(intent);
                     sessionManager.createLoginSession(username, password);
                 }
             }
         }));
    }
}