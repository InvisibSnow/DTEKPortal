package com.dtek.portal.utils;

import java.util.HashMap;
import java.util.Map;
import static com.dtek.portal.utils.Const.Login.API_AUTHORITY;

public class Headers {

    public static Map<String, String> getAuthorityMap() {
        Map<String, String> map = new HashMap<>();
        map.put(API_AUTHORITY, PreferenceUtils.getToken());
        return map;
    }
}
