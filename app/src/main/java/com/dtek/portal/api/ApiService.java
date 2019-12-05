package com.dtek.portal.api;

import com.dtek.portal.models.login.Login;
import com.dtek.portal.models.login.LoginFAQ;
import com.dtek.portal.models.login.ServiceList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

import static com.dtek.portal.utils.Const.Login.API_AUTH_TEXT;

public interface ApiService {

    @GET(API_AUTH_TEXT)
    Call<LoginFAQ> getLoginFAQ();

    @GET()
    Call<Login> getAuth(@Url String url);

    @GET()
    Call<Login> sign(@Url String url);

    @GET()
    Call<ServiceList> getServices(@Url String url);

}
