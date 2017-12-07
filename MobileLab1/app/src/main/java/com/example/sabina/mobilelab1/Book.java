package com.example.sabina.mobilelab1;

/**
 * Created by Sabina on 05/11/2017.
 */

public class Book {
    private String title;
    private String author;
    private Integer year;
    public Book(String t, String a)
    {
        title=t;
        author=a;
    }
    public Book(String t, String a,Integer y)
    {
        title=t;
        author=a;
        year=y;
    }

    public Integer getYear(){return year;}
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

    public void setYear(Integer year) {
        this.year = year;
    }
}
