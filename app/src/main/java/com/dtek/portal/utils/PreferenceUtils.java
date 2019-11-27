package com.dtek.portal.utils;

import androidx.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

public final class PreferenceUtils {

    private static final String TOKEN_KEY = "token";
    private static final String LOGIN = "login";
    private static final String LOGIN_ID = "loginID";
    private static final String USER_PHONE = "userPhone";
    private static final String PHONE_IS_CORPORATE = "isCorporate";

    private PreferenceUtils() {
    }

    //token
    @NonNull
    public static String getToken() {
        return Hawk.get(TOKEN_KEY, "");
    }

    public static void saveToken(@NonNull String token) {
        Hawk.put(TOKEN_KEY, token);
    }

    //login
    @NonNull
    public static String getLogin() {
        return Hawk.get(LOGIN, "");
    }

    public static void saveLogin(String login) {
        Hawk.put(LOGIN, login);
    }

    //login id
    @NonNull
    public static String getLoginId() {
        return Hawk.get(LOGIN_ID, "");
    }

    public static void saveLoginId(String loginId) {
        Hawk.put(LOGIN_ID, loginId);
    }

    //phone
    @NonNull
    public static String getUserPhone() {
        return Hawk.get(USER_PHONE, "");
    }

    public static void saveUserPhone(String userPhone) {
        Hawk.put(USER_PHONE, userPhone);
    }

    //phone is corporate?
    public static boolean isPhoneCorporate() {
        return Hawk.get(PHONE_IS_CORPORATE, false);
    }

    public static void savePhoneCorporate(boolean phoneIsCorporate) {
        Hawk.put(PHONE_IS_CORPORATE, phoneIsCorporate);
    }

}
