package de.itsawade.itsawade.net;

import java.util.List;

import de.itsawade.itsawade.model.BlogPostList;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.model.NewBlogPost;
import de.itsawade.itsawade.model.UserList;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;


public interface PlaceholderService {

    @GET("/api/images/gallery/{id}")
    Call<List<Images>> getImages(@Path("id") int id);

    @GET("/api/posts/")
    Call<BlogPostList> getPosts();

    @GET("/api/images/")
    Call<List<Images>> getAllImages();


    @GET("/api/users")
    Call<UserList> getAllUsers();

    @POST("/api/post/")
    Call<String> postBlogPost(@Body NewBlogPost newBlogPost);

}
