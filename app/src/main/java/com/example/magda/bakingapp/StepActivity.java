package com.example.magda.bakingapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.magda.bakingapp.models.Step;

import java.util.ArrayList;

public class StepActivity extends AppCompatActivity {

    private Step mStep;
    private ArrayList<Step> mSteplist;
    private int mPosition;
    private TextView mReceipeName;
    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_activity);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("step")) {
                mStep = intentThatStartedThisActivity.getParcelableExtra("step");

                StepDetailFragment stepDetailFragment = new StepDetailFragment();
                stepDetailFragment.setMstep(mStep);

                mFragmentManager = getSupportFragmentManager();

                mFragmentManager.beginTransaction()
                        .add(R.id.step_detail_fragment_container, stepDetailFragment)
                        .commit();
            }
            if (intentThatStartedThisActivity.hasExtra("receipe_name")) {
                mReceipeName = (TextView) findViewById(R.id.step_receipe_name);
                mReceipeName.setText(intentThatStartedThisActivity.getStringExtra("receipe_name"));
            }

            if (intentThatStartedThisActivity.hasExtra("step_list")) {
                mSteplist = intentThatStartedThisActivity.getParcelableArrayListExtra("step_list");
            }
            if (intentThatStartedThisActivity.hasExtra("position")) {
                mPosition = intentThatStartedThisActivity.getIntExtra("position", 0);
            }

            BottomNavigationView bottomNavigationView = (BottomNavigationView)
                    findViewById(R.id.bottom_navigation);

            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_previous:
                                    mPosition = mPosition-1;
                                    Step previousStep = mSteplist.get(mPosition);
                                    StepDetailFragment previousStepdetailFragment = new StepDetailFragment();
                                    previousStepdetailFragment.setMstep(previousStep);
                                    mFragmentManager.beginTransaction()
                                            .replace(R.id.step_detail_fragment_container,previousStepdetailFragment)
                                            .commit();
                                case R.id.action_next:
                                    mPosition = mPosition+1;
                                    Step nextStep = mSteplist.get(mPosition);
                                    StepDetailFragment nextStepdetailFragment = new StepDetailFragment();
                                    nextStepdetailFragment.setMstep(nextStep);
                                    mFragmentManager.beginTransaction()
                                            .replace(R.id.step_detail_fragment_container,nextStepdetailFragment)
                                            .commit();

                            }
                            return true;
                        }
                    });



        }
    }

}
