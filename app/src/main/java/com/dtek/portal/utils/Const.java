package com.dtek.portal.utils;

public class Const {

    public static final class HTTP{
        public static final String API_BASE_URL = "https://mobile.dtek.com/";
        private static final String BASE_AUTH = "authorization/auth.svc/";
        public static final String API_AUTH_ACCESS = BASE_AUTH + "checkaccess/";
    }

    public static final class Login{
        public static final String BASE_AUTH = "authorization/auth.svc/";
        public static final String API_AUTH_SMS = BASE_AUTH + "getsms/";
        public static final String API_AUTH_TOKEN = BASE_AUTH + "checksmscode/";
        public static final String API_AUTH_ACCESS = BASE_AUTH + "checkaccess/";
        public static final String API_AUTH_TEXT = BASE_AUTH + "Texts";
        public static final String API_AUTHORITY = "Authority";
    }

    public static final class PUSH {
        public static final String DATA_TYPE = "type";
        public static final String JSON_BODY = "jsonBody";
        public static final String TYPE_HISTORY = "history";
        public static final String TYPE_OPEN = "open";
    }

    public static final class QR {
        public static final String QR_LINK = "https://mobile.dtek.com/MobileApp?";
        public static final String QR_DATA = "url_data";
        public static final String OTHER_REASON = "Другое";
    }

}
