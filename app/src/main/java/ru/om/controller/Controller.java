package ru.om.controller;

import android.util.Log;

import ru.om.model.api.RestApiManager;
import ru.om.model.pojo.Training;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Controller {

    private static final String TAG = Controller.class.getSimpleName();
    private FlowerCallbackListener mListener;
    private RestApiManager mApiManager;

    public Controller(FlowerCallbackListener listener) {
        mListener = listener;
        mApiManager = new RestApiManager();
    }

    public void startFetching() {
        mApiManager.getFlowerApi().getFlowers(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON :: " + s);

                try {
                    JSONArray array = new JSONArray(s);

                    for(int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        Training training = new Training.Builder()
                                .setName(object.getString("name"))
                                .setCategory(object.getString("category"))
                                .setInstructions(object.getString("instructions"))

                                //.setPhoto(object.getString("photo"))
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

    public interface FlowerCallbackListener {

        void onFetchStart();
        void onFetchProgress(Training training);
        void onFetchProgress(List<Training> trainingList);
        void onFetchComplete();
        void onFetchFailed();
    }
}
