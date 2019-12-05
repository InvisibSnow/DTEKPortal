package com.dtek.portal.models.login;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class Login extends BaseObservable {

    @SerializedName("Status")
    @Expose
    private boolean status;

    @SerializedName("IsCorporate")
    @Expose
    private boolean corporate;

    @SerializedName("Tel")
    @Expose
    private String tel;

    @SerializedName("Text")
    @Expose
    private String text;

    @SerializedName("TextUA")
    @Expose
    private String textUa;

    public boolean isStatus() {
        return status;
    }

    public boolean isCorporate() {
        return corporate;
    }

    public String getTel() {
        return tel;
    }

    public String getText() {
        if (Locale.getDefault().getLanguage().equals("ru"))
            return text;
        else {
            if (textUa != null)
                return textUa;
            else
                return text;
        }
    }

}
