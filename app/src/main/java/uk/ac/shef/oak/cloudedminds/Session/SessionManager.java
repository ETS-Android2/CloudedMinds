package uk.ac.shef.oak.cloudedminds.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import uk.ac.shef.oak.cloudedminds.Login;

public class SessionManager {

    SharedPreferences preference;

    SharedPreferences.Editor editor;

    Context _context;

    int Private_mode = 0;

    private static final String PREF_NAME = "AndroidHivePref";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String USERNAME = "username";

    private static final String PASSWORD = "password";

    public SessionManager (Context context) {
        this._context = context;
        preference = _context.getSharedPreferences(PREF_NAME, Private_mode);
        editor = preference.edit();
    }

    public void createLoginSession (String username, String password) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(USERNAME, username);

        editor.putString(PASSWORD, password);

        editor.commit();
    }

    public boolean isLoggedIn(){
        return preference.getBoolean(IS_LOGIN, false);
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(i);
        }
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(USERNAME, preference.getString(USERNAME, null));

        user.put(PASSWORD, preference.getString(PASSWORD, null));

        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }

}
