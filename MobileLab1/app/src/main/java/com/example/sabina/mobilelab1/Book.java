package com.example.sabina.mobilelab1;

/**
 * Created by Sabina on 05/11/2017.
 */

public class Book {
    private String title;
    private String author;
    Integer year;
    public Book(String t, String a)
    {
        title=t;
        author=a;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
