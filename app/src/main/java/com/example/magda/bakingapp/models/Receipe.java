package com.example.magda.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ObjectConstructor;

import java.util.ArrayList;
import java.util.Iterator;
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
    private ArrayList<Ingredient> mIngredients;

    @SerializedName("steps")
    @Expose
    private ArrayList<Step> mSteps;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mServings);
        dest.writeString(mImage);
        dest.writeList(mIngredients);
        dest.writeList(mSteps);
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

    public String getmName() {
        return mName;
    }

    public int getmServings() {
        return mServings;
    }

    public String getmImage() {
        return mImage;
    }

    public ArrayList<Ingredient> getmIngredients() {
        return mIngredients;
    }

    public ArrayList<Step> getmSteps() {
        return mSteps;
    }

    public String ingredientsAsString() {
        StringBuilder builder = new StringBuilder();

        Iterator<Ingredient> ingredientIterator = mIngredients.iterator();

        while(ingredientIterator.hasNext()){
            Ingredient ingredient = ingredientIterator.next();
            builder.append(ingredient.getmIngredient());
            if(ingredientIterator.hasNext()){
                builder.append(" | ");
            }
        }

        return builder.toString();

    }

}


