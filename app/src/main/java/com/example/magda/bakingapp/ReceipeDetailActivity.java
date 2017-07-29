package com.example.magda.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.example.magda.bakingapp.models.Receipe;


public class ReceipeDetailActivity extends AppCompatActivity {

    private Receipe mReceipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipe_detail_activity);



        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("RECEIPE")) {

                mReceipe = intentThatStartedThisActivity.getParcelableExtra("RECEIPE");

                getSupportActionBar().setTitle("Receipe");

                ReceipeDetailFragment detailFragment = new ReceipeDetailFragment();
                detailFragment.setmReceipe(mReceipe);

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.receipe_detail_fragment_container, detailFragment)
                        .commit();

            }
        }

    }
}
