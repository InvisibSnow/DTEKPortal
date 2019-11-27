package com.dtek.portal.utils;

public interface IBaseOnFinishListener {
    void onFailure(String error);
    void onFailure(Throwable throwable);
    void errorToken();
}
