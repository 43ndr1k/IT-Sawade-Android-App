package de.itsawade.itsawade.async;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hendrik on 28.11.15.
 */
public class StringLoader extends AsyncTaskLoader<String> {

    private static final String TAG = "StringLoader";
    private String urlString;

    public StringLoader(Context context,String urlString) {
        super(context);
        this.urlString = urlString;
    }
    @Override
    public String loadInBackground() {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch (java.io.IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return "";
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
