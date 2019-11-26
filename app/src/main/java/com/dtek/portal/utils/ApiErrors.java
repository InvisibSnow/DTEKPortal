package com.dtek.portal.utils;

import androidx.fragment.app.FragmentManager;

import com.dtek.portal.ui.dialog.MyDialog;

import java.net.SocketTimeoutException;
import java.util.Objects;

public class ApiErrors {

    public static void showError(Throwable throwable, FragmentManager fragmentManager) {
        if (throwable.getClass().toString().equals(SocketTimeoutException.class.toString()))
            new MyDialog("Ошибка соеденения! Попробуйте позже!").show(Objects.requireNonNull(fragmentManager), "fragmentDialog");
        else
            new MyDialog("Ошибка сервера! Попробуйте позже!").show(Objects.requireNonNull(fragmentManager), "fragmentDialog");
    }

    public static void showError(String error, FragmentManager fragmentManager) {
        new MyDialog(error).show(Objects.requireNonNull(fragmentManager), "fragmentDialog");
    }

}
