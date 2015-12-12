package de.itsawade.itsawade.net;

import java.util.List;

import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.model.BlogPost;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public interface PlaceholderService {

    @GET("/api/images/gallery/{id}")
    Call<List<Images>> getImages(@Path("id") int id);

    @GET
    Call<List<BlogPost>> getPosts();
}
