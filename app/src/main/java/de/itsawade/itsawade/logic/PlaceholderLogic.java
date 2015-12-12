package de.itsawade.itsawade.logic;

import android.util.Log;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.itsawade.itsawade.net.PlaceholderService;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Steve on 23.11.2015.
 */
public class PlaceholderLogic {

    private static final String TAG = "PlacehodlerLogic";
    private static PlaceholderLogic instance;
    public static PlaceholderLogic getInstance() {
        if(instance == null) {
            instance = new PlaceholderLogic();
        }

        return instance;
    }

    private PlaceholderService service;

    private PlaceholderLogic() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(PlaceholderService.class);
    }

    public List<Post> getPosts() {
        try {
            Response<List<Post>> response = service.getPosts().execute();

            if(response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            Log.e(TAG,e.getMessage(),e);
        }

        return new LinkedList<>();
    }


}
