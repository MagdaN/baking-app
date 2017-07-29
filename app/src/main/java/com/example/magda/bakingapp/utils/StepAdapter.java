package com.example.magda.bakingapp.utils;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magda.bakingapp.R;
import com.example.magda.bakingapp.models.Step;

import java.util.ArrayList;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder> {

    private final Context mContext;
    private ArrayList<Step> mStepList;
    private final StepAdapterOnClickHandler mClickHandler;


    public StepAdapter(Context context, StepAdapterOnClickHandler clickHandler ) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    public interface StepAdapterOnClickHandler {
        void onClick(Step step);
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mId;
        TextView mShortDescription;
        TextView mDescription;

        public StepAdapterViewHolder(View view) {
            super(view);
            Typeface typefaceIngredients = Typeface.createFromAsset(mContext.getAssets(), "fonts/RobotoCondensed-Regular.ttf");

            mShortDescription = (TextView) view.findViewById(R.id.step_short_description);
            mDescription = (TextView) view.findViewById(R.id.step_description);
            mId = (TextView) view.findViewById(R.id.step_id);

            mShortDescription.setTypeface(typefaceIngredients);
            mDescription.setTypeface(typefaceIngredients);
            mId.setTypeface(typefaceIngredients);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Step step = mStepList.get(adapterPosition);
            mClickHandler.onClick(step);
        }
    }

    @Override
    public void onBindViewHolder(StepAdapterViewHolder holder, int position) {
        Step ingredientOnThisPosition = mStepList.get(position);
        int id = ingredientOnThisPosition.getmId();
        String shortDescription = ingredientOnThisPosition.getMshortDescription();
        String description = ingredientOnThisPosition.getmDescription();
        holder.mShortDescription.setText(shortDescription);
        holder.mDescription.setText(description);
        holder.mId.setText(Integer.toString(id) + ".");
    }

    @Override
    public StepAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.step_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new StepAdapter.StepAdapterViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(mStepList == null) {
            return 0;
        } else {
            return mStepList.size();
        }
    }

    public void setmStepList(ArrayList<Step> steps) {
        mStepList = steps;
        notifyDataSetChanged();
    }
}
