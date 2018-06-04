package de.itsawade.itsawade;

import android.app.Application;
import android.content.Context;

/**
 * Created by hendrik on 28.02.16.
 */
public class App extends Application {
    public static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }



}
