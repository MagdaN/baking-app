package com.example.magda.bakingapp.utils;


import com.example.magda.bakingapp.data.Ingredient;
import com.example.magda.bakingapp.data.Receipe;
import com.example.magda.bakingapp.data.Step;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static android.R.attr.path;

public interface BakingApiService {

    @GET("baking.json")
    Call<List<Receipe>> receipies();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
