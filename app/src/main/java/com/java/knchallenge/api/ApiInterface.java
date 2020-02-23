package com.java.knchallenge.api;

import com.java.knchallenge.data.remote.ContactsResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created By Himanshu on 21-02-2020
 */
public interface ApiInterface {

    @GET("db")
    Call<ContactsResponseModel> getContacts();
}
