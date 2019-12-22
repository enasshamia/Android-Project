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
public class TutorialTwoFragment extends Fragment {


    public TutorialTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial_two, container, false);
        view.findViewById(R.id.btn_next).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_next));
        return view;
    }

}
