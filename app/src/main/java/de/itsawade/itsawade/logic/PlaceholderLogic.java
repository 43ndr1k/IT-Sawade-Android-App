package de.itsawade.itsawade.logic;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.itsawade.itsawade.App;
import de.itsawade.itsawade.model.BlogPostList;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.model.NewBlogPost;
import de.itsawade.itsawade.model.UserList;
import de.itsawade.itsawade.net.PlaceholderService;
import de.itsawade.itsawade.util.UserData;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Steve on 23.11.2015.
 */
public class PlaceholderLogic {

    private static final String TAG = "PlacehodlerLogic";
    private static PlaceholderLogic instance;
    private static String authToken;

    private String localhost= "http://192.168.42.90:8000";
    private String web = "http://it-sawade.de";
    public static PlaceholderLogic getInstance() {
        if(instance == null) {
            getToken();
            instance = new PlaceholderLogic();

        }

        return instance;
    }

    private static void getToken() {
        UserData data = new UserData(App.getContext());
        authToken = data.getAccessToken();
    }

    private PlaceholderService service;


    private PlaceholderLogic() {


        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(web)
                        .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient httpClient = new OkHttpClient();
        if (authToken != null) {

            httpClient.interceptors().clear();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();


                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + authToken)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        Retrofit retrofit = builder.client(httpClient).build();
        service = retrofit.create(PlaceholderService.class);

    }

    public List<Images> getImages(int id) {
        try {
            Response<List<Images>> response = service.getImages(id).execute();

            if(response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            Log.e(TAG,e.getMessage(),e);
        }

        return new LinkedList<>();
    }

    public BlogPostList getBlogPostList() {

        try {

            Response<BlogPostList> response = service.getPosts().execute();
            if (response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            Log.e(TAG,e.getMessage());
        }
        return new BlogPostList();
    }

    public List<Images> getAllImages() {
        try {
            Response<List<Images>> response = service.getAllImages().execute();

            if(response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            Log.e(TAG,e.getMessage(),e);
        }
        return new LinkedList<>();
    }

    public UserList getAllUsers() {
        try {
            Response<UserList> response = service.getAllUsers().execute();

            if(response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return new UserList();
    }

    public Integer createNewBlogPost(NewBlogPost post) {

        int ok = 400;
        try {
            Response<ResponseBody> response = service.postBlogPost(post).execute();
            if (response.isSuccess()) {
                ok = 200;
                return ok;
            }
        } catch (IOException e) {
            Log.e(TAG,e.getMessage(),e);
        }
        return ok;
    }



}
