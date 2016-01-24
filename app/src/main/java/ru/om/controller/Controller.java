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
                /**
                 try {
                 final String json = "{\"status\":\"ok\",\"data\":[{\"title\":\"New1\",\"text\":\"text 1\",\"text2\":\"category 1\"},{\"title\":\"New2\",\"text\":\"text 2\",\"text2\":\"category 2\"}]}";
                 final JSONObject jsonObject = new JSONObject(json);
                 final String status = jsonObject.getString("status");
                 final JSONArray jsonArray = jsonObject.getJSONArray("data");
                 final int size = jsonArray.length();
                 final List<Map<String, String>> dataList = new ArrayList<Map<String, String>>(size);
                 Map<String, String> map;
                 for (int i = 0; i < size; i++) {
                 final JSONObject data = jsonArray.getJSONObject(i);
                 map = new HashMap<>();
                 map.put("title", data.getString("title"));
                 map.put("text", data.getString("text"));
                 map.put("text2", data.getString("text2"));
                 dataList.add(map);
                 }
                 } catch (JSONException e) {
                 //
                 }


                 **/


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

        void onFetchStart();
        void onFetchProgress(Training training);
        void onFetchProgress(List<Training> trainingList);
        void onFetchComplete();
        void onFetchFailed();
    }
}
