package com.zefinitii.mynotes;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private FloatingActionButton addNewNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        Retriever retriever = new Retriever(this);
        ArrayList<Note>  notes = retriever.getAllNotes();
        if(notes.size() < 1){
            mainLayout.addView(addTextView(this,"Click on Add button to add new notes",20));
        }
        for( Note note : notes){
            addCardInLinearLayout(this,mainLayout,note.title, note.body, note.id);
        }
        addNewNote = findViewById(R.id.addNewNote);
        addNewNote.setOnClickListener(view -> {
            Intent addItem = new Intent(view.getContext(), AddViewNoteActivity.class);
            view.getContext().startActivity(addItem);
        });
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        System.out.println("hii");
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            System.out.println("hii back key");
            finishAffinity();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void addCardInLinearLayout(Context context, LinearLayout layout, String title, String body,long id){
        Resources res = getResources();
        RelativeLayout.LayoutParams textViewLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        textViewLayout.setMargins(5,5,5,15);
        RelativeLayout.LayoutParams lineLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                5);
//        lineLayout
        RelativeLayout.LayoutParams cardLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        cardLayout.setMargins(5,5,5,5);
        TextView heading = addTextView(context, title, 20);
        View line = new View(context);
        int lineColor = res.getColor(R.color.teal_700);
        line.setBackgroundColor(lineColor);
        TextView detail = addTextView(context, body, 12);
        LinearLayout sabLayout = new LinearLayout(context);
        sabLayout.setOrientation(LinearLayout.VERTICAL);
        sabLayout.addView(heading,textViewLayout);
        sabLayout.addView(line,lineLayout);
        sabLayout.addView(detail, textViewLayout);
        CardView card = new CardView(context);
        card.setId((int)(id));
        card.setOnClickListener(view -> {
            Intent updateItem = new Intent(view.getContext(), AddViewNoteActivity.class);
            updateItem.putExtra("noteId",id);
            view.getContext().startActivity(updateItem);
        });
        card.hasFocus();
        card.hasOnClickListeners();
        card.setPadding(5,5,5,5);
        card.setRadius(8);
        int cardColor = res.getColor(R.color.teal_200);
        card.setCardBackgroundColor(cardColor);
        card.addView(sabLayout,textViewLayout);
        layout.addView(card,cardLayout);
    }
    private TextView addTextView(Context context, String text, int TextSize){
        TextView textView = new TextView(context);
        textView.setTextSize(TextSize);
        textView.setText(text);
        return textView;
    }
}