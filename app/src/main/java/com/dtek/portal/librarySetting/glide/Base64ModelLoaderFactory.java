package com.dtek.portal.librarySetting.glide;

import android.content.Context;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.nio.ByteBuffer;

public class Base64ModelLoaderFactory implements ModelLoaderFactory<String, ByteBuffer> {

    private Context mContext;

    public Base64ModelLoaderFactory(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ModelLoader<String, ByteBuffer> build(MultiModelLoaderFactory unused) {
        return new Base64ModelLoader(mContext);
    }

    @Override
    public void teardown() {
        // Do nothing.
    }
}
