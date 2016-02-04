package ru.om.controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.om.model.api.RestApiManager;
import ru.om.model.pojo.Training;

public class Controller {

    private static final String TAG = Controller.class.getSimpleName();
    private TrainingCallbackListener mListener;
    private RestApiManager mApiManager;

    public Controller(TrainingCallbackListener listener) {
        mListener = listener;
        mApiManager = new RestApiManager();
    }

    public void startFetching() {
        mApiManager.getTrainingApi().getTraining(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON :: " + s);

                try {
                    JSONArray array = new JSONArray(s);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        Training training = new Training.Builder()
                                .setTitle(object.getString("title"))
                                .setText(object.getString("text"))
                                .setUrl(object.getString("v"))
                                .setPhoto(object.getString("v"))
                                .build();

                        mListener.onFetchProgress(training);
                    }

                } catch (JSONException e) {
                    mListener.onFetchFailed();
                }

                mListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();
            }
        });
    }

    public interface TrainingCallbackListener {

        void onFetchProgress(Training training);

        void onFetchComplete();

        void onFetchFailed();
    }
}
