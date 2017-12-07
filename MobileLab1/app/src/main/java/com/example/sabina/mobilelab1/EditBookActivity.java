package com.example.sabina.mobilelab1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {
    EditText author, title;
    //EditText title;
    Button saveButton;
    Integer position, code;

    //Integer code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        init();
        setScreen((Integer) getIntent().getExtras().get("Code"), getIntent().getExtras());

    }

    void setScreen(Integer code, Bundle data) {
        if (code == 1) {
            String titleExtra = data.getString("Title");
            String authorExtra = data.getString("Author");
            position = data.getInt("Position");
            title.setText(titleExtra);
            author.setText(authorExtra);
            saveButton.setText("Save");
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String editedAuthor = author.getText().toString();
                    String editedTitle = title.getText().toString();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("AuthorResult", editedAuthor);
                    returnIntent.putExtra("TitleResult", editedTitle);
                    returnIntent.putExtra("Position", position);
                    returnIntent.putExtra("Code",1);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            });

        } else {
            saveButton.setText("Add");
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("HELLO","Got here");
                    String editedAuthor = author.getText().toString();
                    String editedTitle = title.getText().toString();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("AuthorResult", editedAuthor);
                    returnIntent.putExtra("TitleResult", editedTitle);
                    //returnIntent.putExtra("Position", position);
                    returnIntent.putExtra("Code",2);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            });
        }
    }

    void init() {
        title = (EditText) findViewById(R.id.TitleEditText);
        author = (EditText) findViewById(R.id.AuthorEditText);
        saveButton = (Button) findViewById(R.id.buttonSave);
    }
}
