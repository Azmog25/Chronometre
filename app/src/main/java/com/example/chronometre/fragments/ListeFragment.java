package com.example.chronometre.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chronometre.modal.ChronoModal;
import com.example.chronometre.adapters.ChronoRVAdapter;
import com.example.chronometre.bddManager.DBHandler;
import com.example.chronometre.R;

import java.util.ArrayList;
import java.util.List;

public class ListeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<ChronoModal> liste;
    private DBHandler dbHandler = new DBHandler(getActivity());
    private ChronoRVAdapter chronoRVAdapter;
    private RecyclerView chronoRV;


    public ListeFragment() {
        // Required empty public constructor
    }

    public static ListeFragment newInstance(String param1, String param2) {
        ListeFragment fragment = new ListeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_liste, container, false);
        liste = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());
        liste = dbHandler.readChrono();
        chronoRVAdapter = new ChronoRVAdapter(liste, getActivity());
        chronoRV = view.findViewById(R.id.RVchrono);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        chronoRV.setLayoutManager(linearLayoutManager);
        chronoRV.setAdapter(chronoRVAdapter);
        return view;
    }
}