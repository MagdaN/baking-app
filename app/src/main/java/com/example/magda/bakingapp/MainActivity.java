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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            new FetchRecipiesTask().execute();
        }

    }

    @Override
    public void onReceipeClick(Receipe receipe) {
        Bundle b = new Bundle();
        b.putParcelable("RECEIPE", receipe);
        final Intent intent = new Intent(this, ReceipeDetailActivity.class);
        intent.putExtras(b);
        startActivity(intent);
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
