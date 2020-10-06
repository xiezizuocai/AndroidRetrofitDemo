package com.example.androidretrofitdemo;

import com.example.androidretrofitdemo.domain.CommentItem;
import com.example.androidretrofitdemo.domain.GetWithParamsResult;
import com.example.androidretrofitdemo.domain.JsonResult;
import com.example.androidretrofitdemo.domain.PostFileResult;
import com.example.androidretrofitdemo.domain.PostWithParamsResult;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface API {

    // GET请求方式
    @GET("categories/Girl")
    Call<JsonResult> getJson();

    // GET请求方式，带参数，Query注解
    @GET("get/param")
    Call<GetWithParamsResult> getWithParams(@Query("keyword")String keyword,@Query("page")int page ,@Query("order")String order );

    // GET请求方式，带很多参数，Query注解
    @GET("get/param")
    Call<GetWithParamsResult> getWithParams(@QueryMap Map<String,Object> params);

    @POST("post/string")
    Call<PostWithParamsResult> postWithParams(@Query("string")String content);

    @POST
    Call<PostWithParamsResult> postWithUrl(@Url String url);

    @POST("post/comment")
    Call<PostWithParamsResult> postWithBody(@Body CommentItem commentItem);

    @Multipart
    @POST("/file/upload")
    Call<PostFileResult> postFile(@Part MultipartBody.Part multipartBody);

}
