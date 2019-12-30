package com.example.noteapp.ui.main;


import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
                .child(mAuth.getCurrentUser().getUid())
                .child(bookId);
        FirebaseRecyclerOptions<Note> noteFirebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Note, NoteViewHolder>(noteFirebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note model) {
                model.setNoteId(adapter.getSnapshots().getSnapshot(position).getKey());
                holder.bind(model, bookId);
                showHide(adapter.getItemCount());
            }

            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_note, parent, false);
                return new NoteViewHolder(itemView);
            }
        };
        adapter.startListening();
        rv_notes.setAdapter(adapter);
        return view;
    }

    private void showHide(int visible) {
        if (visible == 0) {
            ll_no_notes.setVisibility(View.VISIBLE);
            rv_notes.setVisibility(View.GONE);
        } else {
            ll_no_notes.setVisibility(View.GONE);
            rv_notes.setVisibility(View.VISIBLE);
        }

    }
}
