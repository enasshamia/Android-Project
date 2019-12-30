package com.example.noteapp.ui.main;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.noteapp.MainActivity;
import com.example.noteapp.R;
import com.example.noteapp.model.Book;
import com.example.noteapp.view.BooksViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.Query;

import static com.example.noteapp.MainActivity.mAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private FloatingActionButton fab_add_book;
    private RecyclerView rv_books;
    private FirebaseRecyclerAdapter<Book, BooksViewHolder> adapter;
    private LinearLayout ll_no_notes;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        rv_books = view.findViewById(R.id.rv_books);
        ll_no_notes = view.findViewById(R.id.ll_no_notes);
        fab_add_book = view.findViewById(R.id.fab_add_book);
        fab_add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navController.navigate(R.id.action_add_book);
            }
        });
        rv_books.setLayoutManager(new GridLayoutManager(view.getContext(), 3));

        Query query = MainActivity.firebaseDatabase.getReference("Books").child(mAuth.getCurrentUser().getUid());

        FirebaseRecyclerOptions<Book> noteFirebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Book>()
                .setQuery(query, Book.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<Book, BooksViewHolder>(noteFirebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull BooksViewHolder holder, int position, @NonNull Book model) {
                showHide(adapter.getItemCount());
                model.setBookId(adapter.getSnapshots().getSnapshot(position).getKey());
                holder.bind(model);
            }

            @NonNull
            @Override
            public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_book, parent, false);
                return new BooksViewHolder(itemView);
            }
        };

        rv_books.setAdapter(adapter);
        adapter.startListening();
        return view;
    }

    private void showHide(int visible) {
        if (visible == 0) {
            ll_no_notes.setVisibility(View.VISIBLE);
            rv_books.setVisibility(View.GONE);
        } else {
            ll_no_notes.setVisibility(View.GONE);
            rv_books.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null)
            adapter.stopListening();
    }
}
