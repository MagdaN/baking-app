package com.example.magda.bakingapp;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magda.bakingapp.models.Receipe;
import com.example.magda.bakingapp.utils.IngredientAdapter;
import com.example.magda.bakingapp.utils.ReceipeAdapter;

public class ReceipeDetailFragment extends Fragment {

    private Receipe mReceipe;
    public static final String CURRENT_RECEIPE = "current_receipe";
    private RecyclerView mRecyclerView;
    private IngredientAdapter mIngredientAdapter;

    public ReceipeDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if(savedInstanceState != null) {
            mReceipe = savedInstanceState.getParcelable(CURRENT_RECEIPE);
        }

        View rootView = inflater.inflate(R.layout.receipe_detail_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.receipe_detail_title);
        if(mReceipe != null) {

            Typeface typefaceName = Typeface.createFromAsset(getContext().getAssets(),"fonts/Slabo27px-Regular.ttf");
            textView.setText(mReceipe.getmName());
            textView.setTypeface(typefaceName);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_ingredients);

            LinearLayoutManager layoutManager =
                    new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);
            mIngredientAdapter = new IngredientAdapter(getContext());
            mRecyclerView.setAdapter(mIngredientAdapter);

            mIngredientAdapter.setmIngredientList(mReceipe.getmIngredients());

        } else {
            Log.v("Magda", "Title null");
        }

        return rootView;
    }

    public void setmReceipe(Receipe mReceipe) {
        this.mReceipe = mReceipe;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_RECEIPE, mReceipe);
    }
}
