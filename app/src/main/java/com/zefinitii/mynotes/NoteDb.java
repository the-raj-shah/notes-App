package com.zefinitii.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDb extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NotesDb";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Note.NoteEntry.TABLE_NAME + " (" +
                    Note.NoteEntry._ID + " INTEGER PRIMARY KEY," +
                    Note.NoteEntry.COLUMN_NAME_TITLE + " TEXT," +
                    Note.NoteEntry.COLUMN_NAME_BODY + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Note.NoteEntry.TABLE_NAME;
    public NoteDb(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // no upgrade
    }
}
