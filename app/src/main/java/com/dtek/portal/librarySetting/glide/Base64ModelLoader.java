package com.dtek.portal.librarySetting.glide;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.nio.ByteBuffer;

public final class Base64ModelLoader implements ModelLoader<String, ByteBuffer> {

    private Context mContext;

    public Base64ModelLoader(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public ModelLoader.LoadData<ByteBuffer> buildLoadData(@NonNull String model, int width, int height, @NonNull Options options) {
        return new LoadData<>(new ObjectKey(model), new Base64DataFetcher(model, mContext));
    }

    @Override
    public boolean handles(@NonNull String model) {
//        return model.startsWith("data:"); // условия для возврата результата
        return true; // у нас все картинки грузятся в Base64, так что всегда возвращаем true
    }
}
