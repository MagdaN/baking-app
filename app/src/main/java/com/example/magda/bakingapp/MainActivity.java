package com.example.magda.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onReceipeClick(Receipe receipe) {
        Bundle b = new Bundle();
        b.putParcelable("RECEIPE", receipe);
        final Intent intent = new Intent(this, ReceipeDetailActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}
