package com.example.noteapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String noteTitle, noteContent;
    private int color;
    private long timestamp;
    private String bookId;
    private String noteId;

    public Note() {
    }


    protected Note(Parcel in) {
        noteTitle = in.readString();
        noteContent = in.readString();
        color = in.readInt();
        timestamp = in.readLong();
        bookId = in.readString();
        noteId = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteTitle);
        dest.writeString(noteContent);
        dest.writeInt(color);
        dest.writeLong(timestamp);
        dest.writeString(bookId);
        dest.writeString(noteId);
    }
}
