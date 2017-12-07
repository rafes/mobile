package com.example.sabina.mobilelab1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class EditBookActivity extends AppCompatActivity {
    EditText author, title;
    //EditText title;
    Button saveButton;
    Integer position, code;
    NumberPicker np;
    //String
    //Integer code;
    String[] months={"Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
    Integer[] pagesRead={0,0,12,20,37,3,0,5,97,12,21,0};
    List<PieEntry> pieEntries=new ArrayList<PieEntry>();
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
            np.setValue(data.getInt("Year"));
            saveButton.setText("Save");
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String editedAuthor = author.getText().toString();
                    String editedTitle = title.getText().toString();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("AuthorResult", editedAuthor);
                    returnIntent.putExtra("TitleResult", editedTitle);
                    returnIntent.putExtra("Year",np.getValue());
                    returnIntent.putExtra("Position", position);
                    returnIntent.putExtra("Code",1);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            });

        } else {
            saveButton.setText("Add");
            np.setValue(2000);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("HELLO","Got here");
                    String editedAuthor = author.getText().toString();
                    String editedTitle = title.getText().toString();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("AuthorResult", editedAuthor);
                    returnIntent.putExtra("TitleResult", editedTitle);
                    returnIntent.putExtra("Year", np.getValue());
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
        np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setMaxValue(2017);
        np.setMinValue(1900);
        for(int i=0;i<months.length;i++)
        {
            pieEntries.add(new PieEntry(pagesRead[i],months[i]));
        }
        PieDataSet ds=new PieDataSet(pieEntries,"Pages Read this Year");
        ds.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(ds);
        PieChart chart= (PieChart) findViewById(R.id.pieChart);
        chart.setData(data);
        chart.invalidate();
        chart.getLegend().setEnabled(false);
    }

}
