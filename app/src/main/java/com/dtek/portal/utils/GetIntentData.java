package com.dtek.portal.utils;

import android.content.Intent;

public class GetIntentData {

    public static void checkIntentData(Intent receivedIntent, Intent sendIntent){
        checkQrData(receivedIntent, sendIntent);
        checkPushNotificationData(receivedIntent, sendIntent);
    }

    private static void checkQrData(Intent receivedIntent, Intent sendIntent){
        if (receivedIntent.getData() != null) {
            sendIntent.putExtra(Const.QR.QR_DATA, receivedIntent.getData().toString());
        }
    }

    private static void checkPushNotificationData(Intent receivedIntent, Intent sendIntent){
        if (receivedIntent.getStringExtra(Const.PUSH.DATA_TYPE) != null &&receivedIntent.getStringExtra(Const.PUSH.JSON_BODY) != null) {
            sendIntent.putExtra(Const.PUSH.DATA_TYPE, receivedIntent.getStringExtra(Const.PUSH.DATA_TYPE));
            sendIntent.putExtra(Const.PUSH.JSON_BODY, receivedIntent.getStringExtra(Const.PUSH.JSON_BODY));
        }
    }



}
