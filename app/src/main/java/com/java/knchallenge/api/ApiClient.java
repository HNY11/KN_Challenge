package com.java.knchallenge.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created By Himanshu on 21-02-2020
 */
public class ApiClient {

    private static final String BASE_URL = "https://my-json-server.typicode.com/HNY11/Rest-Api/";

    private static Retrofit retrofit = null;

    public ApiClient() {
    }

    public synchronized static Retrofit provideRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
