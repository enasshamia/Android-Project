package com.example.noteapp.ui.auth;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialOneFragment extends Fragment {


    public TutorialOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial_one, container, false);

        view.findViewById(R.id.btn_next).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_next));
        view.findViewById(R.id.tv_skip).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_skip));

        return view;
    }

}
