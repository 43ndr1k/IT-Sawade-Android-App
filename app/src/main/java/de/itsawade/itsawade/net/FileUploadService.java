package de.itsawade.itsawade.net;

import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by hendrik on 18.02.16.
 */
public interface FileUploadService {

    @Multipart
    @POST("/upload")
    Call<String> upload(
            @Part("myfile\"; filename=\"image.png\" ") RequestBody file,
            @Part("description") RequestBody description);

}
