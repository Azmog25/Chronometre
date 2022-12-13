package com.example.chronometre.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chronometre.Modal.ChronoModal;
import com.example.chronometre.adapters.ChronoRVAdapter;
import com.example.chronometre.bddManager.DBHandler;
import com.example.chronometre.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<ChronoModal> liste;
    private DBHandler dbHandler = new DBHandler(getActivity());
    private ChronoRVAdapter chronoRVAdapter;
    private RecyclerView chronoRV;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListeFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        // Inflate the layout for this fragment
        return view;
    }
}