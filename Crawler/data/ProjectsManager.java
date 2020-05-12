4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/api/manager/ProjectsManager.java
package com.sketch.code.two.api.manager;

import com.sketch.code.two.api.item.Category;
import com.sketch.code.two.api.item.ProjectInfoResponse;
import com.sketch.code.two.api.item.Projects;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.sketch.code.two.util.Const.BASE_HOST;

public class ProjectsManager {

    private static ProjectsManager mInstance;

    private Retrofit mRetrofit;

    public ProjectsManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ProjectsManager getInstance() {
        if (mInstance == null) {
            mInstance = new ProjectsManager();
        }
        return mInstance;
    }

    public Api getApi () {
        return mRetrofit.create(Api.class);
    }

    public interface Api {

        @GET("https://api.neonteam.net/sketchcode/project/categories")
        Call<ArrayList<Category>> getCategories();

        @GET("https://api.neonteam.net/sketchcode/projects")
        Call<ArrayList<Projects>> getProjects();

        @GET("project/get")
        Call<ProjectInfoResponse> getProject (@Query("id") int id);

    }

}
