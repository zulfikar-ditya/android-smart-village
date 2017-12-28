package com.example.user.smartvillage.service;

import com.example.user.smartvillage.Model.DefaultModel;
import com.example.user.smartvillage.Model.PembangunanModel;
import com.example.user.smartvillage.Model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by user on 28/12/2017.
 */

public class ApiService {
    public static String BASE_URL = "http://192.168.43.84";

    public static PostService service_post = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.PostService.class);

    public static GetService service_get = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.GetService.class);

    public interface PostService{
        @POST("/smartvillage/api/web/v1/auth/login")
        @FormUrlEncoded
        Call<User> postSignIn(@Field("username") String username, @Field("password") String password);

        @POST("/smartvillage/api/web/v1/auth/register")
        @FormUrlEncoded
        Call<DefaultModel> postSignUp(@Field("nik") String nik, @Field("username") String username, @Field("password") String password, @Field("password2") String password2);

        @POST("smartvillage/api/web/v1/request-pembangunan/create")
        @FormUrlEncoded
        Call<DefaultModel> postRequest(
                @Header("Authorization") String auth,
                @Field("judul") String judul, @Field("deskripsi") String deskripsi, @Field("kategori_pembangunan_id") String kategori_pembangunan_id
        );
    }

    public interface GetService{
        @GET("/smartvillage/api/web/v1/pembangunan")
        Call<PembangunanModel> getPembangunan(@Header("Authorization") String auth);
    }

}
