package ru.om.model.api;

import retrofit.Callback;
import retrofit.http.GET;

public interface TrainingApi {

    @GET("/training.htm")
    void getTraining(Callback<String> flowers);
}
