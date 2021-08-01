package uk.ac.shef.oak.cloudedminds.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.HashMap;

import uk.ac.shef.oak.cloudedminds.Login;

/**
 * This class implements a sessions manager through the use of Shared Preferences so that once
 * a particular user is logged in, they will stay logged in even after the application is closed
 * and reopened. It will also allow the user to logout to end the session. Their username can
 * also be retrieved when their entries are saved so they are only able to view their entries in the diary.
 *
 * Based on the tutorial by Ketul Patel on YouTube: https://www.youtube.com/watch?v=SLkQIlfRWgM
 */

public class SessionManager {

    SharedPreferences preference;

    SharedPreferences.Editor editor;

    Context context;

    int private_mode = 0;

    private static final String PREF_NAME = "UserSession";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String USERNAME = "username";

    private static final String PASSWORD = "password";

    // Constructor
    public SessionManager (Context context) {
        this.context = context;
        preference = this.context.getSharedPreferences(PREF_NAME, private_mode);
        editor = preference.edit();
    }

    /**
     * Creates a new user session based on the user details once the user
     * logs in.
     * @param username The username of the user
     * @param password The password of the user
     */
    public void createLoginSession (String username, String password) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(USERNAME, username);

        editor.putString(PASSWORD, password);

        editor.commit();
    }

    public boolean isLoggedIn(){
        return preference.getBoolean(IS_LOGIN, false);
    }

    /**
     * Checks whether the user is already logged in when they visit a screen that
     * requires authentication, if not launches the login screen.
     */
    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i);
        }
    }

    /**
     * Retrieves the username and password of the current user.
     * @return user details.
     */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(USERNAME, preference.getString(USERNAME, null));

        user.put(PASSWORD, preference.getString(PASSWORD, null));

        return user;
    }

    /**
     * Ends the user session and clears the editor. Returns the user
     * back to the Login screen.
     */
    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }

}
