package com.github.denisura.coursera.data.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseraService {
    private CourseraService() {
    }

    public static CourseraApi createCourseraService() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .baseUrl(CourseraApi.BASE_URL)
                .build()
                .create(CourseraApi.class);
    }
}
