package com.dtek.portal.ui.fragment.news.data;

import com.dtek.portal.api.IBase;

public interface INewsListRepo extends IBase {
    void onFinishedNewsListLoad(Boolean load);
}
