package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.rengwuxian.materialedittext.MaterialEditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import uk.ac.shef.oak.cloudedminds.Retrofit.IMyService;
import uk.ac.shef.oak.cloudedminds.Retrofit.RetrofitClient;

/**
 * This class allows the user to sign up to use the application. Once they have created an account,
 * they can login and use the application.
 */
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initiates vibrator
        final Vibrator vibe = (Vibrator) SignUp.this.getSystemService(Context.VIBRATOR_SERVICE);

        // Initiates retrofit client
        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);

        // Retrieves user details from entry fields
        editName = (MaterialEditText)findViewById(R.id.editName);
        editUsername = (MaterialEditText)findViewById(R.id.editUsername2);
        editPassword = (MaterialEditText)findViewById(R.id.editPassword2);

        // Shows and hides the password entered when the button is pressed.
        Button showPass = findViewById(R.id.btnShowPassword2);
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

        // Button to register user onto the app
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
    }


    /**
     * Checks whether the entry fields for user details are empty, if so, provides an error message in
     * an AlertDialogue box. Once the user details are retrieved, it enters the user details into the
     * database, registering the user. It them returns the user to the Login screen to login.
     *
     * @param name user's full name
     * @param username user's username
     * @param password user's password
     */
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