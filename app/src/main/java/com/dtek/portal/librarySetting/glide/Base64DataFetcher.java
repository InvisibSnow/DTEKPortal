package com.dtek.portal.librarySetting.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.dtek.portal.R;
import com.dtek.portal.api.RestManager;
import com.dtek.portal.utils.Base64;

import java.nio.ByteBuffer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Base64DataFetcher implements DataFetcher<ByteBuffer> {

    private final String url;
    private Context mContext;

    public Base64DataFetcher(String url, Context context) {
        this.url = url;
        this.mContext = context;
    }

    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super ByteBuffer> callback) {
        RestManager.getApi()
                .getPhoto(url)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        if(response.isSuccessful() && response.body() != null) {
                            String strBase64 = response.body();
                            ByteBuffer byteBuffer = Base64.getByteBufferFromStrBase64(strBase64);
                            callback.onDataReady(byteBuffer);

                        } else {
                            // метод glide.error не работает если тут ошибка, так что переводим наш ресурс вручную в ByteBuffer,
                            // может и можно передать в другом формате callback.onDataReady(....); но я так и не понял куда это уходит дальше
                            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
                                    R.drawable.img_no_photo);
                            ByteBuffer byteBuffer = Base64.getByteBufferFromBitmap(bitmap);
                            callback.onDataReady(byteBuffer);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                    }
                });
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void cancel() {
    }

    @NonNull
    @Override
    public Class<ByteBuffer> getDataClass() {
        return ByteBuffer.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }
}
