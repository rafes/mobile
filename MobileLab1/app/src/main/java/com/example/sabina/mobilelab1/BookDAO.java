package com.example.sabina.mobilelab1;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Sabina on 07/12/2017.
 */
@Dao
public interface BookDAO {
    @Query("SELECT * FROM book")
    List<Book> getBooks();
    @Insert
    void insert(Book ... books);
    @Delete
    public void deleteBooks(Book... books);
}
