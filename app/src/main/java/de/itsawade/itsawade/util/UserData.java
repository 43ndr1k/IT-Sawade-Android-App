package de.itsawade.itsawade.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;

import de.itsawade.itsawade.R;

/**
 * Created by hendrik on 17.12.15.
 */
public class UserData {

    private static final String PREFS_NAME = "settings";
    private static final String SHPREF_KEY_ACCESS_TOKEN = "access_token";
    private static final String LOGIN_USER = "login_user";
    private String user;
    private SharedPreferences settings;
    private String accessToken = null;


    public UserData(Context context) {
        readLoginData(context);
    }
    public String getUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void readLoginData(Context context) {

        settings = context.getSharedPreferences(PREFS_NAME, context.MODE_PRIVATE);
        accessToken = settings.getString(SHPREF_KEY_ACCESS_TOKEN, null);
        user = settings.getString(LOGIN_USER, null);

        if (accessToken == null || user == null || accessToken == "" || user == "") {
            // need to get access token with OAuth2.0
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage(context.getResources().getString(R.string.login));
            builder1.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();

        }
    }
}
