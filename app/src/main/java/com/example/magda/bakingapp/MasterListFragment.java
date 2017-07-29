package com.example.magda.bakingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(CURRENT_RECEPEIS, mReceipeAdapter.getValues());
    }

}
