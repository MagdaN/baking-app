package com.example.magda.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magda.bakingapp.models.Receipe;
import com.example.magda.bakingapp.utils.ReceipeAdapter;

import java.util.ArrayList;


public class MasterListFragment extends Fragment {

    private ArrayList<Receipe> mReceipeList;
    private RecyclerView mRecyclerView;
    private ReceipeAdapter mReceipeAdapter;

    public static final String CURRENT_RECEPEIS = "current_recepeis";



    public MasterListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.master_list_fragment, container, false);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_receipes);

        int numberOfColumns = calculateNoOfColumns(getContext());

        GridLayoutManager layoutManager =
                new GridLayoutManager(getContext(), numberOfColumns);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);
        mReceipeAdapter = new ReceipeAdapter(getContext(), (MainActivity) getActivity());
        mRecyclerView.setAdapter(mReceipeAdapter);

        if(savedInstanceState !=null) {
            ArrayList<Receipe> current_recepeis = savedInstanceState.getParcelableArrayList(CURRENT_RECEPEIS);
            mReceipeAdapter.setmRecepies(current_recepeis);
        } else {
            mReceipeList = getArguments().getParcelableArrayList("receipies");
            mReceipeAdapter.setmRecepies(mReceipeList);
        }

        return rootView;
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 360);
        return noOfColumns;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(CURRENT_RECEPEIS, mReceipeAdapter.getValues());
    }

}
