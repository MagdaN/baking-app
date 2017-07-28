package com.example.magda.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.os.PersistableBundle;
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

    private ArrayList<Receipe> mReceipies;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.baking_app_linear_layout) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }

        if(savedInstanceState == null) {
            new FetchRecipiesTask().execute();
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

    private class FetchRecipiesTask extends AsyncTask<String, Void, ArrayList<Receipe>> {

        @Override
        protected void onPostExecute(ArrayList<Receipe> receipiesData) {
            if (receipiesData != null) {

                mReceipies = receipiesData;

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("receipies",mReceipies);

                FragmentManager fragmentManager = getSupportFragmentManager();
                MasterListFragment listFragment = new MasterListFragment();
                listFragment.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .add(R.id.master_list_fragment, listFragment)
                        .commit();

                if(mTwoPane==true) {
                    ReceipeDetailFragment detailFragment = new ReceipeDetailFragment();
                    detailFragment.setmReceipe(mReceipies.get(0));

                    fragmentManager.beginTransaction()
                        .add(R.id.receipe_detail_fragment_container, detailFragment)
                        .commit();
                }

            }
        }

        @Override
        protected ArrayList<Receipe> doInBackground(String... params) {
            BakingApiService bakingApi = BakingApiService.retrofit.create(BakingApiService.class);
            Call<ArrayList<Receipe>> call = bakingApi.receipies();
            try {
                ArrayList<Receipe> result = call.execute().body();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


}
