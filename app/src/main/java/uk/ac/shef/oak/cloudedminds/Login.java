package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
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

/**
 * This class allows the user to login in order to access the application. Once logged in,
 * it starts a user session so that the user stays logged in even after they exit the app
 * without logging out.
 */
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager initialised
        sessionManager = new SessionManager(getApplicationContext());

        final Vibrator vibe = (Vibrator) Login.this.getSystemService(Context.VIBRATOR_SERVICE);

        // Button to open the sign up screen
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

        signup.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        signup.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        signup.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });


        // Initiates the retrofit client
        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);

        // Retrieves the text from the username and password entry fields
        editUsername = (MaterialEditText)findViewById(R.id.editUsername);
        editPassword = (MaterialEditText)findViewById(R.id.editPassword);

        // Shows and hides the password entered when the button is pressed.
        Button showPass = findViewById(R.id.btnShowPassword);
        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    showPass.setText("HIDE");

                    //Show Password
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    showPass.setText("SHOW");

                    //Hide Password
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

        // Button to login user based on entered username and password
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

        login.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        login.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        login.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }

    /**
     * Checks whether the username or password entry fields are empty, if so, displays an AlertDialog box
     * informing the user to enter them. Once the username and password are retrieved, they are compared with
     * the users in the database and should they exist, they will be logged in and taken to the main screen.
     * Alongside this, their session will be created. If the username or password is incorrect, the relevant
     * AlertBox with the error will be shown to the user.
     *
     * @param username The username entered by the user
     * @param password The password entered by the user
     */
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
                     startActivity(new Intent(getApplicationContext(), MainActivity.class));
                     sessionManager.createLoginSession(username, password);
                 }
             }
         }));
    }
}