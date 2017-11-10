package com.example.sabina.hillcipher;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Encryptor encryptor=new Encryptor();
    EditText plainText;
    EditText encryptedText;
    EditText keyEncryption;
    EditText keyDecryption;
    Button encryptButton;
    Button decryptButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encryptor=new Encryptor();
        plainText= (EditText) findViewById(R.id.plainText);
        encryptedText= (EditText) findViewById(R.id.encryptedEditText);
        keyEncryption= (EditText) findViewById(R.id.keyText);
        keyDecryption = (EditText) findViewById(R.id.decryptEditText);
        encryptButton = (Button) findViewById(R.id.buttonEncrypt);
        decryptButton = (Button) findViewById(R.id.decryptButton);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plainTxt=plainText.getText().toString().toLowerCase();
                String key=keyEncryption.getText().toString().toLowerCase();
                if(!encryptor.isValid(plainTxt)){
                    String invalidCharaters=encryptor.invalidCharacters(plainTxt);
                    showDialogBox(invalidCharaters,1, "plain text");
                }
                else if (key.length()!=4){
                    showDialogBox("Your encryption key must contain 4 characters!",0,"encryption key");
                }
                else if(!encryptor.isValid(key))
                {
                    String invalidChacters=encryptor.invalidCharacters(key);
                    showDialogBox(invalidChacters,1,"encryption key");
                }
                else if(!encryptor.keyIsValid(key)){
                    showDialogBox("Encryption key is not valid! Try another combination .",0,"encryption key");
                }
                else
                {
                    String result=encryptor.calculateEncryption(plainTxt,key);
                    showDialogBox(result,3,"Encrypted Successfully");
                }

            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ecryptedTxt=encryptedText.getText().toString().toLowerCase();
                String key=keyDecryption.getText().toString().toLowerCase();
                if(ecryptedTxt.length()<4)
                {
                    showDialogBox("Your encrypted text must contain 4 characters!",0,"encrypted text");
                }
                else if(!encryptor.isValid(ecryptedTxt)){
                    String invalidCharaters=encryptor.invalidCharacters(ecryptedTxt);
                    showDialogBox(invalidCharaters,1, "encrypted text");
                }
                else if (key.length()<4){
                    showDialogBox("Your decryption key must contain 4 characters!",0,"decryption key");
                }
                else if(!encryptor.isValid(key))
                {
                    String invalidChacters=encryptor.invalidCharacters(key);
                    showDialogBox(invalidChacters,1,"Decryption key");
                }
                else if(!encryptor.keyIsValid(key))
                {
                    showDialogBox("Decryption key is not valid! Try another combination .",0,"Decryption key");
                }
                else
                {
                    String description=encryptor.calculateDescryption(ecryptedTxt,key);
                    showDialogBox(description,3,"Decrypted Successfully");
                }
            }
        });
    }

    void showDialogBox(String message, int code , String textType)
    {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(MainActivity.this);
        if(code==1) {
            builder.setTitle("Incorrect "+textType)
                    .setMessage("You are not allowed to use characters like "+message+" for the "+textType)
                    .setNegativeButton("Try again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    }).show();
        }
        if(code==0){
            builder.setTitle("Incorrect "+ textType)
                    .setMessage(message)
                    .setNegativeButton("Try again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    }).show();
        }

        if(code==3)
        {
            builder.setTitle(textType)
                    .setMessage("Result is : "+message.toUpperCase())
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    }).show();
        }

    }
}
