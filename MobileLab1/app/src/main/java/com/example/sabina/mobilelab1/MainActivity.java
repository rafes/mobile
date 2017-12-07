package com.example.sabina.mobilelab1;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText email;
    EditText subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email= (EditText) findViewById(R.id.receiver);
        subject=(EditText) findViewById(R.id.subject);
        Button composeButton= (Button) findViewById(R.id.button);
        composeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(subject.getText()!=null &&email.getText()!=null)
                {
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setData(Uri.parse("mailto:"));
                    String[] to={email.getText().toString()};
                    sendIntent.putExtra(Intent.EXTRA_EMAIL,to);
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                    sendIntent.putExtra(Intent.EXTRA_TEXT,"<<Insert message here...>>");
                    sendIntent.setType("plain/text");
                    //sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                    startActivity(Intent.createChooser(sendIntent,"Send Email"));
                }
            }
        });

        Button goToList= (Button) findViewById(R.id.goToList);
        goToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent=new Intent(MainActivity.this,ListActivity.class);
                startActivity(listIntent);
            }
        });
    }








}


