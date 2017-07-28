package com.example.magda.bakingapp.utils;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magda.bakingapp.R;
import com.example.magda.bakingapp.models.Ingredient;

import java.util.ArrayList;


public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder> {

    private final Context mContext;
    private ArrayList<Ingredient> mIngredientList;

    public IngredientAdapter(Context context) {
        mContext = context;
    }


    public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mIngredient;

        public IngredientAdapterViewHolder(View view) {
            super(view);
            Typeface typefaceIngredients = Typeface.createFromAsset(mContext.getAssets(), "fonts/RobotoCondensed-Regular.ttf");
            mIngredient = (TextView) view.findViewById(R.id.ingredient);
        }

    }

    @Override
    public void onBindViewHolder(IngredientAdapterViewHolder holder, int position) {
        Ingredient ingredientOnThisPosition = mIngredientList.get(position);
        String ingredient = ingredientOnThisPosition.getmIngredient();
        String measure = ingredientOnThisPosition.getmMeasure();
        Float quantity = ingredientOnThisPosition.getmQuantity();
        holder.mIngredient.setText(Float.toString(quantity) + " " + measure + " " + ingredient);
    }

    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.ingredient_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new IngredientAdapter.IngredientAdapterViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(mIngredientList == null) {
            return 0;
        } else {
            return mIngredientList.size();
        }
    }

    public void setmIngredientList(ArrayList<Ingredient> ingredients) {
        mIngredientList = ingredients;
        notifyDataSetChanged();
    }
}
