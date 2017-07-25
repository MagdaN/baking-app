package com.example.magda.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.magda.bakingapp.models.Receipe;
import com.example.magda.bakingapp.utils.BakingApiService;
import com.example.magda.bakingapp.utils.ReceipeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class MainActivity extends AppCompatActivity implements ReceipeAdapter.ReceipeAdapterOnClickHandler {

    private boolean mTwoPane;
    private ReceipeDetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.baking_app_linear_layout) != null) {
            mTwoPane = true;

            ReceipeDetailFragment detailFragment = new ReceipeDetailFragment();
            //detailFragment.setmReceipe(mReceipe);

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.receipe_detail_fragment_container, detailFragment)
                    .commit();
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onReceipeClick(Receipe receipe) {
        if(mTwoPane == false) {
            Bundle b = new Bundle();
            b.putParcelable("RECEIPE", receipe);
            final Intent intent = new Intent(this, ReceipeDetailActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        } else {
            ReceipeDetailFragment newFragment = new ReceipeDetailFragment();
            newFragment.setmReceipe(receipe);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.receipe_detail_fragment_container,newFragment)
                    .commit();
        }
    }

    public boolean ismTwoPane() {
        return mTwoPane;
    }

    public ReceipeDetailFragment getDetailFragment() {
        return detailFragment;
    }

    public void setDetailFragment(ReceipeDetailFragment detailFragment) {
        this.detailFragment = detailFragment;
    }
}
