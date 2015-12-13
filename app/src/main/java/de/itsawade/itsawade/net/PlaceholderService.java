package de.itsawade.itsawade.net;

import java.util.List;

import de.itsawade.itsawade.model.BlogPostList;
import de.itsawade.itsawade.model.Images;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public interface PlaceholderService {

    @GET("/api/images/gallery/{id}")
    Call<List<Images>> getImages(@Path("id") int id);

    @GET("/api/posts/")
    Call<BlogPostList> getPosts();
}
