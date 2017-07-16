package com.example.magda.bakingapp;

import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.magda.bakingapp.models.Receipe;
import com.example.magda.bakingapp.utils.BakingApiService;
import com.example.magda.bakingapp.utils.ReceipeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Receipe> mReceipeList;
    private RecyclerView mRecyclerView;
    private ReceipeAdapter mReceipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceipeList = new ArrayList<Receipe>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_receipes);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mReceipeAdapter = new ReceipeAdapter(this);
        mRecyclerView.setAdapter(mReceipeAdapter);

        if(savedInstanceState !=null) {
            ArrayList<Receipe> current_recepeis = savedInstanceState.getParcelableArrayList("CURRENT_RECEPEIS");
            mReceipeAdapter.setmRecepies(current_recepeis);
        } else {

            new FetchRecipiesTask().execute();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("CURRENT_RECEPEIS", mReceipeAdapter.getValues());
    }


    private class FetchRecipiesTask extends AsyncTask<String, Void, List<Receipe>> {

        @Override
        protected void onPostExecute(List<Receipe> receipiesData) {
            if (receipiesData != null) {
                mReceipeAdapter.setmRecepies((ArrayList<Receipe>) receipiesData);

            }
        }

        @Override
        protected List<Receipe> doInBackground(String... params) {

            BakingApiService bakingApi = BakingApiService.retrofit.create(BakingApiService.class);
            Call<List<Receipe>> call = bakingApi.receipies();
            try {
                List<Receipe> result = call.execute().body();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
