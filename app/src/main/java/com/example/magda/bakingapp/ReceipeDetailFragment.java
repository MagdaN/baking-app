package com.example.magda.bakingapp;


import android.content.Context;
import android.content.Intent;
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
import com.example.magda.bakingapp.models.Step;
import com.example.magda.bakingapp.utils.IngredientAdapter;
import com.example.magda.bakingapp.utils.StepAdapter;

public class ReceipeDetailFragment extends Fragment implements StepAdapter.StepAdapterOnClickHandler {

    private Receipe mReceipe;
    public static final String CURRENT_RECEIPE = "current_receipe";
    private RecyclerView mIngredientsRecyclerView;
    private IngredientAdapter mIngredientAdapter;
    private RecyclerView mStepsRecyclerView;
    private StepAdapter mStepsAdapter;

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
            Typeface typefaceIngredients = Typeface.createFromAsset(getContext().getAssets(), "fonts/RobotoCondensed-Regular.ttf");
            textView.setText(mReceipe.getmName());
            textView.setTypeface(typefaceName);

            TextView ingredientsSection = (TextView) rootView.findViewById(R.id.ingredients_section_heading);
            TextView stepsSection = (TextView) rootView.findViewById(R.id.steps_section_heading);

            ingredientsSection.setTypeface(typefaceIngredients);
            stepsSection.setTypeface(typefaceIngredients);

            mIngredientsRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_ingredients);

            LinearLayoutManager ingredientLayoutManager =
                    new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mIngredientsRecyclerView.setLayoutManager(ingredientLayoutManager);
            mIngredientsRecyclerView.setHasFixedSize(false);
            mIngredientsRecyclerView.setNestedScrollingEnabled(false);
            mIngredientAdapter = new IngredientAdapter(getContext());
            mIngredientsRecyclerView.setAdapter(mIngredientAdapter);

            mIngredientAdapter.setmIngredientList(mReceipe.getmIngredients());

            mStepsRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_steps);

            LinearLayoutManager stepLayoutManager =
                    new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mStepsRecyclerView.setLayoutManager(stepLayoutManager);
            mStepsRecyclerView.setHasFixedSize(false);
            mStepsRecyclerView.setNestedScrollingEnabled(false);
            mStepsAdapter = new StepAdapter(getContext(), this);
            mStepsRecyclerView.setAdapter(mStepsAdapter);

            mStepsAdapter.setmStepList(mReceipe.getmSteps());

        }

        return rootView;
    }

    @Override
    public void onClick(Step step) {
        Context context = getActivity();
        Class destinationClass = StepActivity.class;
        Intent intentToStartStepActivity = new Intent(context, destinationClass);
        intentToStartStepActivity.putExtra("step", step);
        intentToStartStepActivity.putExtra("receipe_name", mReceipe.getmName());
        getActivity().startActivity(intentToStartStepActivity);
    }

    public void setmReceipe(Receipe mReceipe) {
        this.mReceipe = mReceipe;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_RECEIPE, mReceipe);
    }
}
