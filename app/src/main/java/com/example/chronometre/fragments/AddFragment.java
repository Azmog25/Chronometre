package com.example.chronometre.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chronometre.bddManager.DBHandler;
import com.example.chronometre.R;

public class AddFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText chronoName, chronoTimer;
    private Button addChronoBtn;
    private DBHandler dbHandler;


    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        initUI(v);
        return v;
    }

    private void initUI(View v) {
        chronoName = v.findViewById(R.id.EdtChronoName);
        addChronoBtn = v.findViewById(R.id.BtnAddChrono);
        dbHandler = new DBHandler(getActivity());

        addChronoBtn.setOnClickListener(view -> {
            String name = chronoName.getText().toString();
            if(name.isEmpty()) {
                Toast.makeText(getActivity(), "Please, enter a name for this timer", Toast.LENGTH_SHORT).show();
                return;
            }
            dbHandler.addNewChrono(name, "00:00");
            Toast.makeText(getActivity(), "Timer has been added", Toast.LENGTH_SHORT).show();
            chronoName.setText("");
        });
    }
}