package com.dtek.portal.models.news;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dtek.portal.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News extends BaseObservable implements Parcelable {

    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("Category")
    @Expose
    private String category;

    @SerializedName("Subtitle")
    @Expose
    private String subtitle;

    @SerializedName("Body")
    @Expose
    private String body;

    @SerializedName("PublishedDate")
    @Expose
    private String publishedDate;

    @SerializedName("NumComments")
    @Expose
    private Integer numComments;

    @SerializedName("LikesCount")
    @Expose
    private Integer likesCount;

    @SerializedName("Image")
    @Expose
    private String imageLink;

    private Bitmap picture;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getBody() {
        return body;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Bitmap getPicture() {
        return picture;
    }

    protected News(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        category = in.readString();
        subtitle = in.readString();
        body = in.readString();
        publishedDate = in.readString();
        if (in.readByte() == 0) {
            numComments = null;
        } else {
            numComments = in.readInt();
        }
        if (in.readByte() == 0) {
            likesCount = null;
        } else {
            likesCount = in.readInt();
        }
        imageLink = in.readString();
        picture = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(title);
        dest.writeString(category);
        dest.writeString(subtitle);
        dest.writeString(body);
        dest.writeString(publishedDate);
        if (numComments == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numComments);
        }
        if (likesCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(likesCount);
        }
        dest.writeString(imageLink);
        dest.writeParcelable(picture, flags);
    }

    public boolean isDbHas(){
        //todo get from database
        return false;
    }

    @BindingAdapter("toggleButton")
    public static void bindToggleButton(ToggleButton toggleButton, News news){
        toggleButton.setOnClickListener(v -> {
            if(toggleButton.isChecked()){
                Log.d("MyLOG"," Delete News from db");
            } else {
                Log.d("MyLOG"," Save News to db");
            }
        });
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .error(R.drawable.img_no_photo)
                .centerCrop()
                .into(view);
    }

    public static final DiffUtil.ItemCallback<News> CALLBACK = new DiffUtil.ItemCallback<News>() {
        @Override
        public boolean areItemsTheSame(@NonNull News news, @NonNull News t1) {
            return news.getId().equals(t1.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull News news, @NonNull News t1) {
            return true;
        }
    };
}
