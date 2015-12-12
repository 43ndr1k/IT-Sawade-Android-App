package de.itsawade.itsawade.async;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.net.PlaceholderService;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by hendrik on 28.11.15.
 */
public class ImagesLoader extends AsyncTaskLoader<List<Images>> {

    private static final String TAG = "ImagesLoader";
    private int id;

    public ImagesLoader(Context contex, int id) {
        super(contex);
        this.id = id;
    }

    @Override
    public List<Images> loadInBackground() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://it-sawade.de")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceholderService placeholderService = retrofit.create(PlaceholderService.class);

        try {
            Response<List<Images>> response = placeholderService.getImages(id).execute();
            if(response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
