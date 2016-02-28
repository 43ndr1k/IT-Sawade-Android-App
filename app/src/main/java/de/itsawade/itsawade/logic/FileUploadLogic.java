package de.itsawade.itsawade.logic;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by hendrik on 18.02.16.
 */
public class FileUploadLogic {


    public static final String API_BASE_URL = "http://your.api-base.url";

     OkHttpClient httpClient = new OkHttpClient();


/*
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    FileUploadService service =
            ServiceGenerator.createService(FileUploadService.class);

    String descriptionString = "hello, this is description speaking";
    RequestBody description =
            RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

    File file = new File("path/to/your/file");
    RequestBody requestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), file);

    Call<String> call = service.upload(requestBody, description);
    call.enqueue(new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            Log.v("Upload", "success");
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            Log.e("Upload", t.getMessage());
        }
    });*/

}
