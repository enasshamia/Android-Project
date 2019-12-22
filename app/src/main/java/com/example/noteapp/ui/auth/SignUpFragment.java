package com.example.noteapp.ui.auth;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.noteapp.MainActivity;
import com.example.noteapp.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private TextInputEditText et_email;
    private TextInputEditText et_password;
    private Button btn_sign_up;
    private String email, password;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        btn_sign_up = view.findViewById(R.id.btn_sign_up);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_email.getText().toString();
                password = et_password.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    et_email.setError(getString(R.string.input_error_email));
                    et_email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    et_email.setError(getString(R.string.input_error_email_invalid));
                    et_email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    et_email.setError(getString(R.string.input_error_password));
                    et_email.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    et_password.setError(getString(R.string.input_error_password_length));
                    et_password.requestFocus();
                    return;
                }
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        return view;
    }

}
