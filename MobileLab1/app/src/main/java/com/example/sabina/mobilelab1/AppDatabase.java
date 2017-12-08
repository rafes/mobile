package com.example.sabina.mobilelab1;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Sabina on 07/12/2017.
 */
@Database(entities = {Book.class},version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDAO bookDAO();

}
