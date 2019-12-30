package com.example.noteapp.view;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.R;
import com.example.noteapp.model.Book;


public class BooksViewHolder extends RecyclerView.ViewHolder {
    private ImageView iv_book;
    private TextView tv_title;
    private LinearLayout container;

    public BooksViewHolder(@NonNull View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.container);
        iv_book = itemView.findViewById(R.id.iv_image);
        tv_title = itemView.findViewById(R.id.tv_title);
    }

    public void bind(Book model) {
        tv_title.setText(model.getBookTitle());
        if (model.getBookColor().equals("")) {
            model.setBookColor("0");
        }
        Drawable drawable = itemView.getContext().getDrawable(getBookColor(Integer.parseInt(model.getBookColor())));
        iv_book.setImageDrawable(drawable);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private int getBookColor(int color) {
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
                return R.drawable.g_color_1;
        }
    }
}
