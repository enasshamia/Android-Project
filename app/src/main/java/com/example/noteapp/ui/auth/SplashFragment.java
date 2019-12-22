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
public class SplashFragment extends Fragment {


    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        view.findViewById(R.id.btn_sign_in).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_sign_in));
        view.findViewById(R.id.tv_sign_up).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_sign_up));
        return view;
    }
}
