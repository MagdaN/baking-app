package com.example.magda.bakingapp.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step implements Parcelable {

    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("shortDescription")
    @Expose
    private String mshortDescription;

    @SerializedName("description")
    @Expose
    private String mDescription;

    @SerializedName("videoURL")
    @Expose
    private String mVideoURL;

    @SerializedName("thumbnailURL")
    @Expose
    private String mThumbnailURL;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mshortDescription);
        dest.writeString(mDescription);
        dest.writeString(mVideoURL);
        dest.writeString(mThumbnailURL);

    }

    private Step(Parcel in) {
        mId = in.readInt();
        mshortDescription = in.readString();
        mDescription = in.readString();
        mVideoURL = in.readString();
        mThumbnailURL = in.readString();
    }


    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}
