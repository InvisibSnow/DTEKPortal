package com.dtek.portal.api;

import com.dtek.portal.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dtek.portal.utils.Const.HTTP.API_BASE_URL;

public class RestManager {

    private static final int CONNECT_TIMEOUT = 120; // timeout prod
    private static final int CONNECT_TIMEOUT_DEBUG = 60; // timeout debug

    public static ApiService getApi() {
        return getApiService("yyyy-MM-dd'T'HH:mm:ss");
    }

    public static ApiService getApiWithTimeZone(){
        return getApiService("yyyy-MM-dd'T'HH:mm:ssZ");
    }

    public static ApiService getApiSimpleFormatDate(){
        return getApiService("dd.MM.yyyy");
    }

    private static ApiService getApiService(String dateFormet){
        Gson gson = new GsonBuilder()
                .setDateFormat(dateFormet)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ApiService.class);
    }

    private static OkHttpClient getClient() {
        return new OkHttpClient
                .Builder()
                .connectTimeout(getTimeout(), TimeUnit.SECONDS)
                .writeTimeout(getTimeout(), TimeUnit.SECONDS)
                .readTimeout(getTimeout(), TimeUnit.SECONDS)
                .addInterceptor(getHttpLoggingInterceptor())
                .build();
    }

    private static int getTimeout(){
        if (BuildConfig.DEBUG) {
            return CONNECT_TIMEOUT_DEBUG;
        } else {
            return CONNECT_TIMEOUT;
        }
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }


}
