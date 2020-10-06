package com.example.androidretrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidretrofitdemo.domain.JsonResult;

import java.net.HttpURLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://gank.io/api/v2/";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getRequest(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<JsonResult> task = api.getJson();

        task.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                Log.d(TAG,"getRequest onResponse code -> " + response.code());

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    JsonResult result = response.body();
                    Log.d(TAG,"getRequest onResponse json -> " + result);

                    updateList(result);
                }
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                Log.d(TAG,"getRequest onFailure -> " + t.toString());

            }
        });


    }

    private void updateList(JsonResult jsonResult) {
        List<JsonResult.DataBean> dataBeanList = jsonResult.getData();

        Log.d(TAG,"updateList dataBeanList size -> "  + dataBeanList.size());

        for (JsonResult.DataBean dataBean : dataBeanList) {
            String id = dataBean.get_id();
            Log.d(TAG,"updateList dataBean id -> "  + id);
        }



    }

}
