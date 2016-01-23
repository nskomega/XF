package ru.om.model.api;

import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import ru.om.model.utilities.Constants;

public class RestApiManager {

    private TrainingApi mTrainingApi;

    public TrainingApi getTrainingApi() {

        if (mTrainingApi == null) {
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(String.class, new StringDesirializer());

            mTrainingApi = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setConverter(new GsonConverter(gson.create()))
                    .build()
                    .create(TrainingApi.class);
        }
        return mTrainingApi;
    }

}
