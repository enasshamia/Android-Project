package com.example.noteapp.ui.main;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.noteapp.MainActivity;
import com.example.noteapp.R;
import com.example.noteapp.model.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;


import java.util.Date;

import static com.example.noteapp.MainActivity.firebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends Fragment {
    private EditText et_title, et_content;
    private TextView tv_timestamp;
    private Note note = new Note();
    private FloatingActionButton fab_save;
    private String bookId, noteId;
    private View view;

    public AddNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_note, container, false);
        initView();
        AddNoteFragmentArgs args = AddNoteFragmentArgs.fromBundle(getArguments());
        bookId = args.getBookId();
        note.setBookId(bookId);
        if (args.getNote() != null) {
            note = args.getNote();
            initNote(note);
        } else {
            initNote();
        }

        return view;
    }

    private void initNote() {
        Date date = new Date(System.currentTimeMillis());
        tv_timestamp.setText(date.toString());
        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String content = et_content.getText().toString();
                if (TextUtils.isEmpty(title)) {
                    return;
                }
                note.setNoteTitle(title);
                note.setNoteContent(content);
                note.setTimestamp(System.currentTimeMillis());
                note.setBookId(bookId);
                note.setNoteId("");
                firebaseDatabase.getReference("Notes")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .push()
                        .setValue(note)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                MainActivity.navController.navigate(MainFragmentDirections.actionNotes(bookId));
                            }
                        });
            }
        });
    }

    private void initView() {
        et_title = view.findViewById(R.id.et_title);
        et_content = view.findViewById(R.id.et_content);
        tv_timestamp = view.findViewById(R.id.tv_timestamp);
        fab_save = view.findViewById(R.id.fab_save);
    }

    private void initNote(final Note note) {
        et_title.setText(note.getNoteTitle());
        et_content.setText(note.getNoteContent());
        tv_timestamp.setText(new Date(note.getTimestamp()).toString());
        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String content = et_content.getText().toString();
                if (TextUtils.isEmpty(title)) {
                    return;
                }
                note.setNoteTitle(title);
                note.setNoteContent(content);
                note.setTimestamp(System.currentTimeMillis());
                note.setBookId(bookId);
                note.setNoteId("");
                firebaseDatabase.getReference("Notes")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child(note.getNoteId())
                        .setValue(note)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                MainActivity.navController.navigate(MainFragmentDirections.actionNotes(bookId));
                            }
                        });
            }
        });
    }

}
