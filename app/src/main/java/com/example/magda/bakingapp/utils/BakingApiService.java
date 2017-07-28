package com.example.magda.bakingapp.utils;


import com.example.magda.bakingapp.models.Receipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface BakingApiService {

    @GET("baking.json")
    Call<ArrayList<Receipe>> receipies();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
