4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/api/manager/UserManager.java
package com.sketch.code.two.api.manager;

import com.sketch.code.two.api.item.BaseResponse;
import com.sketch.code.two.api.item.User;
import com.sketch.code.two.api.item.UserResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.sketch.code.two.util.Const.BASE_HOST;

public class UserManager {

    private static UserManager mInstance;

    private Retrofit mRetrofit;

    private UserManager () {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static UserManager getInstance() {
        if (mInstance == null) {
            mInstance = new UserManager();
        }
        return mInstance;
    }

    public UserManagerApi getUserManagerApi () {
        return mRetrofit.create(UserManagerApi.class);
    }

    public interface UserManagerApi {

        @FormUrlEncoded
        @POST("user/get")
        Call<UserResponse> get(
                @Field("token") String token,
                @Field("id") int id
        );

        @FormUrlEncoded
        @POST("user/get")
        Call<UserResponse> get(
                @Field("token") String token
        );

        @FormUrlEncoded
        @POST("https://api.neonteam.net/sketchcode/user/auth/register")
        Call<BaseResponse> register(
                @Field("email") String email,
                @Field("password") String password,
                @Field("first_name") String first_name,
                @Field("surname") String surname
        );

        @FormUrlEncoded
        @POST("user/auth/login")
        Call<BaseResponse> login(
                @Field("email") String email,
                @Field("password") String password
        );

        // FIXME
        @POST("user/edit")
        Call<BaseResponse> edit(@Body User user);

        // FIXME
        @FormUrlEncoded
        @POST("user/edit")
        Call<BaseResponse> edit(
                @Field("token") String token,
                @Field("first_name") String first_name,
                @Field("surname") String surname,
                @Field("bio") String bio
        );

        @FormUrlEncoded
        @POST("user/auth/logout")
        Call<BaseResponse> logout (@Field("token") String token);

    }

}
