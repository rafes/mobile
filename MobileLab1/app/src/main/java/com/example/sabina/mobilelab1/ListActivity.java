package com.example.sabina.mobilelab1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    String WHY = "working";
    Adapter custumAdapter;
    List<Book> mybooks = new ArrayList<>();
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView books = (ListView) findViewById(R.id.bookList);
        addButton = (Button) findViewById(R.id.addBookButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
        initialiseBookList();
        custumAdapter = new Adapter(mybooks);
        books.setAdapter(custumAdapter);
        books.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewBook(mybooks.get(position), position);
                //Log.d(WHY,"got here ");
            }
        });
    }

    void viewBook(Book book, int pos) {
        Intent intent = new Intent(this, EditBookActivity.class);
        intent.putExtra("Author", book.getAuthor());
        intent.putExtra("Title", book.getTitle());
        intent.putExtra("Position", pos);
        intent.putExtra("Code", 1);
        startActivityForResult(intent, 1);
    }

    void addBook()
    {
        Intent intent = new Intent(this, EditBookActivity.class);
        intent.putExtra("Code", 2);
        startActivityForResult(intent, 2);
    }

    void initialiseBookList()
    {
        Book b1=new Book("Sabina","Paul Goma");
        Book b2=new Book("First Grave","Darynda Jones");
        Book b3=new Book("Where women are kings","Christie Watson");
        Book b4=new Book("Algoritmica Amuzanta","Paul Goma");
        Book b5=new Book("Woman in Red","Eileen Goudge");
        Book b6=new Book("The phantom of the opera","Gaston Leroux");
        mybooks.add(b1);
        mybooks.add(b2);
        mybooks.add(b3);
        mybooks.add(b4);
        mybooks.add(b5);
        mybooks.add(b6);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.e(WHY,"intra in onActivityResult");
        if (requestCode == 1) {
            Log.e(WHY,"intra in requestCode==1");
            if(resultCode == Activity.RESULT_OK){
                Bundle result=data.getExtras();
                String Resulttitle=result.getString("TitleResult");
                String Resultauthor=result.getString("AuthorResult");
                Integer position=result.getInt("Position");
                mybooks.get(position).setAuthor(Resultauthor);
                mybooks.get(position).setTitle(Resulttitle);
                custumAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Log.e(WHY,"intra in requestCode==CANCELED");
            }

        }
        if(requestCode==2)
        {  Log.e(WHY,"intra in requestCode==2");
            if(resultCode == Activity.RESULT_OK) {
                Log.e(WHY,"o ajuns aici da mere oare ?");
                Bundle result = data.getExtras();
                String Resulttitle = result.getString("TitleResult");
                String Resultauthor = result.getString("AuthorResult");
                Book b = new Book(Resulttitle, Resultauthor);
                mybooks.add(b);
                custumAdapter.notifyDataSetChanged();
            }

        }
    }

    class Adapter extends BaseAdapter
    {

        private List<Book> elements;

        public Adapter(List<Book> el)
        {
            elements=el;
        }
        @Override
        public int getCount() {
            return elements.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView =getLayoutInflater().inflate(R.layout.book_layout,null);
            TextView t1= (TextView) convertView.findViewById(R.id.textView);
            TextView t2= (TextView) convertView.findViewById(R.id.textView2);
            Button deleteButton= (Button) convertView.findViewById(R.id.buttonDeleteBook);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    elements.remove(position);
                    notifyDataSetChanged();
                }
            });
            t1.setText(elements.get(position).getTitle());
            t2.setText(elements.get(position).getAuthor());
            return convertView;
        }
    }
}
