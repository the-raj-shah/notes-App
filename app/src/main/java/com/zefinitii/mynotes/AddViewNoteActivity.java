package com.zefinitii.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class AddViewNoteActivity extends AppCompatActivity {
    private MaterialButton saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view_note);
        Retriever retriever = new Retriever(this);
        Intent intent = getIntent();
        long noteId = intent.getLongExtra("noteId",-1);
        System.out.println(noteId);
        if(noteId != -1){
            Note note = retriever.getNoteById(noteId);
            System.out.println(note.title);
            System.out.println(note.body);
            EditText titleElement = (EditText) findViewById(R.id.title);
            EditText bodyElement = (EditText)findViewById(R.id.body);
            System.out.println(titleElement);
            titleElement.setText(note.title, TextView.BufferType.EDITABLE);
            bodyElement.setText(note.body, TextView.BufferType.EDITABLE);
        }
        saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(view -> {
            EditText titleElement = (EditText)findViewById(R.id.title);
            EditText bodyElement =(EditText) findViewById(R.id.body);
            String title = titleElement.getText().toString();
            String body = bodyElement.getText().toString();
            long id;
            if(noteId != -1){
                id = retriever.updateNote(title,body,noteId);
                System.out.println(id);
            }else{
                id = retriever.insertIntoDb(title,body);
                System.out.println(id);
            }

            Intent addItem = new Intent(view.getContext(), MainActivity.class);
            view.getContext().startActivity(addItem);
        });

    }
}