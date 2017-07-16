package com.example.magda.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient implements Parcelable {

    @SerializedName("measure")
    @Expose
    private String mMeasure;

    @SerializedName("quantity")
    @Expose
    private Float mQuantity;

    @SerializedName("ingredient")
    @Expose
    private String mIngredient;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mMeasure);
        dest.writeFloat(mQuantity);
        dest.writeString(mIngredient);

    }

    private Ingredient(Parcel in) {
        mMeasure = in.readString();
        mQuantity = in.readFloat();
        mIngredient = in.readString();
    }


    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

}
