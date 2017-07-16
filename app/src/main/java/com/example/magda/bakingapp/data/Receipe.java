package com.example.magda.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Receipe implements Parcelable {

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("servings")
    @Expose
    private int mServings;

    @SerializedName("image")
    @Expose
    private String mImage;

    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> mIngredients;

    @SerializedName("steps")
    @Expose
    private List<Step> mSteps;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mServings);
        dest.writeString(mImage);
        dest.writeTypedList(mIngredients);
        dest.writeTypedList(mSteps);
    }

    private Receipe(Parcel in) {
        mName = in.readString();
        mServings = in.readInt();
        mImage = in.readString();

        mIngredients = new ArrayList<Ingredient>();
        in.readList(mIngredients, Ingredient.class.getClassLoader());

        mSteps = new ArrayList<Step>();
        in.readList(mSteps, Step.class.getClassLoader());
    }


    public static final Parcelable.Creator<Receipe> CREATOR = new Parcelable.Creator<Receipe>() {
        public Receipe createFromParcel(Parcel in) {
            return new Receipe(in);
        }

        public Receipe[] newArray(int size) {
            return new Receipe[size];
        }
    };
}

