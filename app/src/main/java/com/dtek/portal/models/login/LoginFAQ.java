package com.dtek.portal.models.login;

import android.text.Html;
import android.text.Spanned;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class LoginFAQ extends BaseObservable {

    @SerializedName("Sms")
    @Expose
    private String sms;

    @SerializedName("SmsUA")
    @Expose
    private String smsUa;

    @SerializedName("FAQ")
    @Expose
    private String faq;

    @SerializedName("FAQUA")
    @Expose
    private String faqUa;

    public Spanned getSms() {
        if (Locale.getDefault().getLanguage().equals("ru"))
            return Html.fromHtml(sms);
        else
            return Html.fromHtml(smsUa);
    }

    public Spanned getFaq() {
        String FAQ;
        if (Locale.getDefault().getLanguage().equals("ru"))
            FAQ = faq;
        else
            FAQ = faqUa;
        return Html.fromHtml(
                "<b>" + "FAQ" + "</b><br><br>" + FAQ);
    }
}
