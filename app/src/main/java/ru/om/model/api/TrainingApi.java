package ru.om.model.api;

import retrofit.Callback;
import retrofit.http.GET;

public interface TrainingApi {

    @GET("/feeds/flowers.json")
    void getFlowers(Callback<String> flowers);
}
