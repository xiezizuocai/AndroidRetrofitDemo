package com.example.androidretrofitdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidretrofitdemo.domain.CommentItem;
import com.example.androidretrofitdemo.domain.GetWithParamsResult;
import com.example.androidretrofitdemo.domain.PostFileResult;
import com.example.androidretrofitdemo.domain.PostWithParamsResult;
import com.example.androidretrofitdemo.utils.RetrofitManager;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestActivity extends AppCompatActivity {

    private static final String TAG = "RequestActivity";
    private static final int REQUEST_CODE = 1;
    private API mApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_avtivity);
        mApi = RetrofitManager.getRetrofit().create(API.class);

        int permissionResult = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionResult != PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_CODE) {

        }

    }

    public void getWithParamsQuery(View view) {
        Call<GetWithParamsResult> withParams = mApi.getWithParams("我是搜索的关键字..", 10, "1");
        withParams.enqueue(new Callback<GetWithParamsResult>() {
            @Override
            public void onResponse(Call<GetWithParamsResult> call, Response<GetWithParamsResult> response) {

                Log.d(TAG,"getWithParamsResult onResponse code -> " + response.code());
                if (response.code()== HttpURLConnection.HTTP_OK) {
                    GetWithParamsResult getWithParamsResult = response.body();
                    Log.d(TAG,"getWithParamsResult onResponse -> " + getWithParamsResult.toString());
                }
            }

            @Override
            public void onFailure(Call<GetWithParamsResult> call, Throwable t) {
                Log.d(TAG,"getWithParamsResult onFailure -> " + t.toString());
            }
        });

    }



    public void getWithParamsQueryMap(View view) {
        Map<String,Object> map = new HashMap<>();
        map.put("keyword","我是搜索的关键字..");
        map.put("page",10);
        map.put("order","1");

        Call<GetWithParamsResult> withParams = mApi.getWithParams(map);
        withParams.enqueue(new Callback<GetWithParamsResult>() {
            @Override
            public void onResponse(Call<GetWithParamsResult> call, Response<GetWithParamsResult> response) {

                Log.d(TAG,"getWithParamsQueryMap onResponse code -> " + response.code());
                if (response.code()== HttpURLConnection.HTTP_OK) {
                    GetWithParamsResult getWithParamsResult = response.body();
                    Log.d(TAG,"getWithParamsQueryMap onResponse -> " + getWithParamsResult.toString());
                }
            }

            @Override
            public void onFailure(Call<GetWithParamsResult> call, Throwable t) {
                Log.d(TAG,"getWithParamsQueryMap onFailure -> " + t.toString());
            }
        });

    }


    public void postWithParams(View view) {

        Call<PostWithParamsResult> withParams = mApi.postWithParams("Post 请求提交的字符串。");
        withParams.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {

                Log.d(TAG,"postWithParams onResponse code -> " + response.code());
                if (response.code()== HttpURLConnection.HTTP_OK) {
                    PostWithParamsResult postWithParamsResult = response.body();
                    Log.d(TAG,"postWithParams onResponse -> " + postWithParamsResult.toString());
                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG,"postWithParams onFailure -> " + t.toString());
            }
        });
    }


    public void postWithUrl(View view) {
        Call<PostWithParamsResult> task = mApi.postWithUrl("post/string?string=POST请求Url方式请求内容");

        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                Log.d(TAG,"postWithUrl onResponse code -> " + response.code());

                if (response.code()== HttpURLConnection.HTTP_OK) {
                    String string = response.body().toString();
                    Log.d(TAG,"postWithParams onResponse -> " + string);
                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG,"postWithUrl onFailure -> " + t.toString());
            }
        });
    }


    public void postWithBody(View view) {
        CommentItem commentItem = new CommentItem("224444(文章ID)","评论的内容");

        Call<PostWithParamsResult> task = mApi.postWithBody(commentItem);

        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                Log.d(TAG,"postWithBody onResponse code -> " + response.code());
                if (response.code()== HttpURLConnection.HTTP_OK) {
                    String result = response.body().toString();
                    Log.d(TAG,"postWithBody onResponse result -> " + result);

                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG,"postWithBody onFailure -> " + t.toString());

            }
        });

    }


    public void postFile(View view) {

        File file = new File("/sdcard/tieba/052E57EF1F4A90EAEC9DCD2AD2C8F468.jpg");
        RequestBody requestBody = RequestBody.create(file,MediaType.parse("image/jpeg"));
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file"," ",requestBody);

        Call<PostFileResult> task = mApi.postFile(multipartBody);
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                int code = response.code();
                Log.d(TAG,"postFile onResponse code -> " + response.code());

                if (code== HttpURLConnection.HTTP_OK) {
                    String result = response.body().toString();

                }
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG,"postFile onFailure -> " + t.toString());

            }
        });

    }


}
