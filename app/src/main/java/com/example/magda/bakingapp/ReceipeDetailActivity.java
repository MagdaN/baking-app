package com.example.magda.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.example.magda.bakingapp.models.Receipe;
import com.example.magda.bakingapp.models.Step;
import com.example.magda.bakingapp.utils.StepAdapter;


public class ReceipeDetailActivity extends AppCompatActivity implements StepAdapter.StepAdapterOnClickHandler {

    private Receipe mReceipe;
    private boolean mTwoPane;
    private Step mStep;
    private FragmentManager mFragmentManager;
    private StepDetailFragment mStepFragment;
    private ReceipeDetailFragment mReceipeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipe_detail_activity);
        getSupportActionBar().setTitle("Receipe");

        if (findViewById(R.id.receipe_detail_linear_layout) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }

        if(savedInstanceState == null) {

            Intent intentThatStartedThisActivity = getIntent();


            if (intentThatStartedThisActivity != null) {
                if (intentThatStartedThisActivity.hasExtra("RECEIPE")) {
                    mReceipe = intentThatStartedThisActivity.getParcelableExtra("RECEIPE");
                    startReceipeDetailFragment();
                    if (mTwoPane == true) {
                        mStep = mReceipe.getmSteps().get(0);
                        startStepDetailFragment();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(Step step, int position) {
        if(mTwoPane == false) {
            Context context = this;
            Class destinationClass = StepActivity.class;
            Intent intentToStartStepActivity = new Intent(context, destinationClass);
            intentToStartStepActivity.putExtra("step", step);
            intentToStartStepActivity.putExtra("receipe_name", mReceipe.getmName());
            intentToStartStepActivity.putExtra("step_list", mReceipe.getmSteps());
            intentToStartStepActivity.putExtra("position", position);
            startActivity(intentToStartStepActivity);
        } else {
            mStepFragment = new StepDetailFragment();
            mStepFragment.setMstep(step);
            mFragmentManager.beginTransaction()
                    .replace(R.id.step_detail_fragment_container, mStepFragment)
                    .commit();

        }

    }

    private void startStepDetailFragment() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }

        if (mStepFragment == null) {
            mStepFragment = new StepDetailFragment();
            mStepFragment.setMstep(mStep);
            mFragmentManager.beginTransaction()
                    .add(R.id.step_detail_fragment_container, mStepFragment)
                    .commit();
        }

    }

    private void startReceipeDetailFragment() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }

        if (mReceipeFragment == null) {
            mReceipeFragment = new ReceipeDetailFragment();
            mReceipeFragment.setmReceipe(mReceipe);
            mFragmentManager.beginTransaction()
                    .add(R.id.receipe_detail_fragment_container, mReceipeFragment)
                    .commit();
        }

    }
}
