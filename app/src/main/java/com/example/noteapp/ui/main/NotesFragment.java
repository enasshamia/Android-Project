package com.example.noteapp.ui.main;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.noteapp.MainActivity;
import com.example.noteapp.R;
import com.example.noteapp.model.Book;
import com.example.noteapp.model.Note;
import com.example.noteapp.view.BooksViewHolder;
import com.example.noteapp.view.NoteViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.Query;

import static com.example.noteapp.MainActivity.mAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {

    private FloatingActionButton fab_add_note;
    private RecyclerView rv_notes;
    private FirebaseRecyclerAdapter<Note, NoteViewHolder> adapter;
    private LinearLayout ll_no_notes;
    private String bookId;

    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        NotesFragmentArgs args = NotesFragmentArgs.fromBundle(getArguments());
        bookId = args.getBookId();
        rv_notes = view.findViewById(R.id.rv_notes);
        rv_notes.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ll_no_notes = view.findViewById(R.id.ll_no_notes);
        fab_add_note = view.findViewById(R.id.fab_add_note);
        fab_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navController.navigate(NotesFragmentDirections.actionAddEditNote(bookId, null));
            }
        });

        Query query = MainActivity.firebaseDatabase
                .getReference("Notes")
                .child(mAuth.getCurrentUser().getUid()).equalTo("bookId", bookId);

        return view;
    }

}
