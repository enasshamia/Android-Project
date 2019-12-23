package com.example.noteapp.ui.main;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.MainActivity;
import com.example.noteapp.R;
import com.example.noteapp.model.Note;
import com.example.noteapp.view.NoteViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static com.example.noteapp.MainActivity.mAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private RecyclerView rv_notes;
    private FirebaseRecyclerAdapter<Note, NoteViewHolder> adapter;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        rv_notes = view.findViewById(R.id.rv_notes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        rv_notes.setLayoutManager(linearLayoutManager);

        Query query = MainActivity.firebaseDatabase.getReference(mAuth.getCurrentUser().getUid());

        FirebaseRecyclerOptions<Note> noteFirebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<Note, NoteViewHolder>(noteFirebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note model) {
                holder.bind(model);
            }
            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_note, parent, false);
                return new NoteViewHolder(itemView);
            }
        };
        rv_notes.setAdapter(adapter);
        adapter.startListening();
        return view;
    }

}
