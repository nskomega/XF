package ru.om.model.api;

import retrofit.Callback;
import retrofit.http.GET;

public interface TrainingApi {

    @GET("/api/videos.json")
    void getFlowers(Callback<String> flowers);
}
