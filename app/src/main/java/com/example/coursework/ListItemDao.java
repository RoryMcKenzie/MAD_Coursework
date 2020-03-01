package com.example.coursework;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ListItemDao {

    @Insert
    void insert(ListItem listItem);

    @Query("DELETE FROM list_table")
    void deleteAll();

    @Query("SELECT * from list_table")
    LiveData<List<ListItem>> getListItems();

}
