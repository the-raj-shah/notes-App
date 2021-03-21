package com.zefinitii.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class Retriever {
    Context context;
    public Retriever(Context context) {
        this.context = context;
    }
    public long insertIntoDb(String NoteTitle, String NoteBody){
       NoteDb noteDb = new NoteDb(this.context);
        SQLiteDatabase db = noteDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.NoteEntry.COLUMN_NAME_TITLE, NoteTitle);
        values.put(Note.NoteEntry.COLUMN_NAME_BODY, NoteBody);

        long newRowId = db.insert(Note.NoteEntry.TABLE_NAME, null, values);
        return newRowId;
    }
    public int updateNote(String NoteTitle, String NoteBody, long id){
        NoteDb noteDb = new NoteDb(this.context);
        SQLiteDatabase db = noteDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.NoteEntry.COLUMN_NAME_TITLE, NoteTitle);
        values.put(Note.NoteEntry.COLUMN_NAME_BODY, NoteBody);
        String selection = Note.NoteEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        int update = db.update(Note.NoteEntry.TABLE_NAME,values, selection, selectionArgs);
        return update;
    }
    public ArrayList<Note> getAllNotes(){
        NoteDb noteDb = new NoteDb(this.context);
        SQLiteDatabase db = noteDb.getReadableDatabase();

//        String[] projection = {
//                BaseColumns._ID,
//                Note.NoteEntry.COLUMN_NAME_TITLE,
//                Note.NoteEntry.COLUMN_NAME_BODY
//        };
        String sortOrder =  Note.NoteEntry._ID + " DESC";
    Cursor cursor = db.query(Note.NoteEntry.TABLE_NAME, null, null, null,null,null,sortOrder);
        ArrayList<Note> notes = new ArrayList<Note>();
        while(cursor.moveToNext()) {
            long noteId = cursor.getLong(cursor.getColumnIndexOrThrow(Note.NoteEntry._ID));
            String noteTitle = cursor.getString(cursor.getColumnIndexOrThrow(Note.NoteEntry.COLUMN_NAME_TITLE));
            String noteBody = cursor.getString(cursor.getColumnIndexOrThrow(Note.NoteEntry.COLUMN_NAME_BODY));
            System.out.println(String.valueOf(noteId)+noteTitle+noteBody);
            notes.add(new Note(noteId, noteTitle, noteBody));
        }
        cursor.close();
        return notes;
    }
    public Note getNoteById (long id) {
        NoteDb noteDb = new NoteDb(this.context);
        SQLiteDatabase db = noteDb.getReadableDatabase();
        String selection = Note.NoteEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(Note.NoteEntry.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        long noteId = cursor.getLong(cursor.getColumnIndexOrThrow(Note.NoteEntry._ID));
        String noteTitle = cursor.getString(cursor.getColumnIndexOrThrow(Note.NoteEntry.COLUMN_NAME_TITLE));
        String noteBody = cursor.getString(cursor.getColumnIndexOrThrow(Note.NoteEntry.COLUMN_NAME_BODY));
        return new Note(noteId, noteTitle, noteBody);
    }
}
