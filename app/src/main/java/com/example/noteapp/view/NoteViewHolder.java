package com.example.noteapp.view;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.MainActivity;
import com.example.noteapp.R;
import com.example.noteapp.model.Note;
import com.example.noteapp.ui.main.MainFragment;
import com.example.noteapp.ui.main.NotesFragmentDirections;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private CircleImageView civ_color;
    private TextView tv_title, tv_content, tv_date;
    private CardView container;
    private String bookId;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.container);
        civ_color = itemView.findViewById(R.id.civ_color);
        tv_title = itemView.findViewById(R.id.tv_title);
        tv_content = itemView.findViewById(R.id.tv_content);
        tv_date = itemView.findViewById(R.id.timestamp);
    }

    public void bind(final Note note, final String bookId) {
        this.bookId = bookId;
        tv_title.setText(note.getNoteTitle());
        tv_content.setText(note.getNoteContent());
        civ_color.setImageResource(getColor(note.getColor()));
        Date date = new Date(note.getTimestamp());
        tv_date.setText(date.toString());
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navController.navigate(NotesFragmentDirections.actionAddEditNote(bookId, note));
            }
        });
    }

    private int getColor(int color) {
        switch (color) {
            case 1:
                return R.drawable.g_color_1;
            case 2:
                return R.drawable.g_color_2;
            case 3:
                return R.drawable.g_color_3;
            case 4:
                return R.drawable.g_color_4;
            default:
                return 0;
        }
    }
}
