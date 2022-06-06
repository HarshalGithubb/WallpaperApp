package com.example.wallpaper2_app;

import static com.example.wallpaper2_app.ApiUtilities.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL="https://api.pexels.com/v1/";

    @Headers("Authorization: "+API)
    @GET("curated")
        //getImage is Function created by us
    Call<PhotosModel> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );


    @Headers("Authorization: "+API)
    @GET("search")
        //getImage is Function created by us
    Call<PhotosModel> getSearchImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );




}
