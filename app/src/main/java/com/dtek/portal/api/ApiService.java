package com.dtek.portal.api;

import com.dtek.portal.models.login.ServiceList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

//    @GET()
//    Call<Login> getAuth(@Url String url);

    @GET()
    Call<ServiceList> getServices(@Url String url);

}
