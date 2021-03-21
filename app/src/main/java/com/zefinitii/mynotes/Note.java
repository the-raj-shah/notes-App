package com.zefinitii.mynotes;

import android.provider.BaseColumns;

public class Note {
    public long id;
    public  String title;
    public String body;
    public Note(long id, String title, String  body){
        this.id = id;
        this.title = title;
        this.body = body;
    }
    public Note(){}
    public static class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "Note";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_BODY = "body";
    }
}
