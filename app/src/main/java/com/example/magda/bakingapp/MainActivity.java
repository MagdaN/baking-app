package com.example.magda.bakingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magda.bakingapp.data.Receipe;
import com.example.magda.bakingapp.utils.BakingApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Receipe> mReceipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mReceipeList = new ArrayList<Receipe>();

        setContentView(R.layout.activity_main);
        new FetchRecipiesTask().execute();
    }

    private class FetchRecipiesTask extends AsyncTask<String, Void, List<Receipe>> {

        @Override
        protected void onPostExecute(List<Receipe> receipiesData) {

            super.onPostExecute(receipiesData);
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
