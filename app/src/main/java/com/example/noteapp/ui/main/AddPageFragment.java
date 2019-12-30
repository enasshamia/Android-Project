package com.example.noteapp.ui.main;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.noteapp.MainActivity;
import com.example.noteapp.R;
import com.example.noteapp.model.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPageFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "AddPage";
    private MaterialButton btn_save;
    private EditText et_book_title;
    private Book book = new Book();
    private CircleImageView color1, color2, color3;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public AddPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_page, container, false);
        btn_save = view.findViewById(R.id.btn_save_book);
        btn_save.setOnClickListener(this);
        et_book_title = view.findViewById(R.id.et_book_title);
        color1 = view.findViewById(R.id.color_1);
        color1.setOnClickListener(this);
        color2 = view.findViewById(R.id.color_2);
        color2.setOnClickListener(this);
        color3 = view.findViewById(R.id.color_3);
        color3.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.color_1:
                book.setBookColor("1");
                break;
            case R.id.color_2:
                book.setBookColor("2");
                break;
            case R.id.color_3:
                book.setBookColor("3");
                break;
            case R.id.btn_save_book:
                saveBook();
                Log.d(TAG, "onClick: ");
            default:
                book.setBookColor("0");
                break;
        }
    }

    private void saveBook() {
        if (TextUtils.isEmpty(et_book_title.getText())) {
            return;
        }
        if (TextUtils.isEmpty(et_book_title.getText())) {
            return;
        }
        book.setBookTitle(et_book_title.getText().toString());
        firebaseDatabase.getReference("Books")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .push()
                .setValue(book)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        MainActivity.navController.navigate(R.id.mainFragment);
                    }
                });
    }
}
