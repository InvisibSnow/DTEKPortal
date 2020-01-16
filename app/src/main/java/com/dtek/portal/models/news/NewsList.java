package com.dtek.portal.models.news;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsList extends BaseObservable {

    @SerializedName("nextPage")
    @Expose
    private Boolean nextPage;

    @SerializedName("news")
    @Expose
    private List<News> newses = new ArrayList<>();

    public Boolean getNextPage() {
        return nextPage;
    }

    public List<News> getNewses() {
        return newses;
    }
}
