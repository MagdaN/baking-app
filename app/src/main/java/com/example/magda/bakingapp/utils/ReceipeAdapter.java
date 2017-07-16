package com.example.magda.bakingapp.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magda.bakingapp.R;
import com.example.magda.bakingapp.models.Receipe;

import java.util.ArrayList;


public class ReceipeAdapter extends RecyclerView.Adapter<ReceipeAdapter.ReceipeAdapterViewHolder> {

    private ArrayList<Receipe> mRecepies;
    private final Context mContext;

    public ReceipeAdapter(Context context) {
        mContext = context;
    }


    public class ReceipeAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mReceipeName;
        public final TextView mIngredients;



        public ReceipeAdapterViewHolder(View view) {
            super(view);
            mReceipeName = (TextView) view.findViewById(R.id.receipe_name);
            mIngredients = (TextView) view.findViewById(R.id.receipe_ingredients);
        }

    }

    @Override
    public void onBindViewHolder(ReceipeAdapterViewHolder holder, int position) {
        Receipe receipeOnThisPosition = mRecepies.get(position);
        String name = receipeOnThisPosition.getmName();
        String igredients = receipeOnThisPosition.ingredientsAsString();
        holder.mReceipeName.setText(name);
        holder.mIngredients.setText(igredients);
    }

    @Override
    public ReceipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.receipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new ReceipeAdapterViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mRecepies == null) {
            return 0;
        }
        return mRecepies.size();
    }

    public void setmRecepies(ArrayList<Receipe> receipes) {
        mRecepies = receipes;
        notifyDataSetChanged();
    }

}
