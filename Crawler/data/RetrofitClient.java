2
https://raw.githubusercontent.com/RzTutul/Covid-19/master/app/src/main/java/com/example/covid19/helper/RetrofitClient.java
package com.example.covid19.helper;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL ="https://corona.lmao.ninja/v2/";

    public  static Retrofit getRetrofitClient()
    {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
