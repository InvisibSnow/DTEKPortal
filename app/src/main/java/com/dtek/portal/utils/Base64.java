package com.dtek.portal.utils;

import android.graphics.Bitmap;

import com.dtek.portal.api.RestManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static com.dtek.portal.utils.Const.Login.API_AUTHORITY;

public class Base64 {

    // URL -> Base64
    public static String getBase64FromUrl(String url) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put(API_AUTHORITY, PreferenceUtils.getToken());
        String strBase64 = null;
        retrofit2.Response<String> response = RestManager.getApi()
                .getImageHtml(map, url)
                .execute();
        if (response.isSuccessful() && response.body() != null) {
            strBase64 = response.body();
        }
        return strBase64;
    }

    // Base64 -> bytes
    public static byte[] getBytesFromBase64(String strBase64) {
        byte[] bytes = new byte[0];
        try {
            bytes = android.util.Base64.decode(strBase64, android.util.Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    // bitmap -> ByteBuffer
    public static ByteBuffer getByteBufferFromBitmap(Bitmap bitmap){
        byte [] bytes = getPictureByteOfArray(bitmap);
        return ByteBuffer.wrap(bytes);
    }

    // strBase64 -> ByteBuffer
    public static ByteBuffer getByteBufferFromStrBase64(String strBase64){
        byte [] bytes = getBytesFromBase64(strBase64);
        return ByteBuffer.wrap(bytes);
    }

    // bitmap -> byte
    public static byte[] getPictureByteOfArray(Bitmap bitmap) {
        byte[] bytes = new byte[0];
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
