package com.example.sabina.mobilelab1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Sabina on 05/11/2017.
 */
@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int bid;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name="author")
    private String author;
    @ColumnInfo(name="year")
    private Integer year;

    public Book(){

    }
    public Book(String title, String author,Integer year)
    {
        this.title=title;
        this.author=author;
        this.year=year;
    }

    public int getBid(){return bid;}
    public void setBid(int bid){this.bid=bid;}
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
