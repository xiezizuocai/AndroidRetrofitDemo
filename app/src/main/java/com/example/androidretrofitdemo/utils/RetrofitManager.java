package com.example.androidretrofitdemo.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl("https://gank.io/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getRetrofit(){
        return sRetrofit;
    }


}
