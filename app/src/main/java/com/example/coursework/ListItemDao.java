package com.example.coursework;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListItemDao {

    @Insert
    void insert(ListItem listItem);

    @Query("DELETE FROM list_table")
    void deleteAll();

    @Query("SELECT * from list_table ORDER BY id DESC")
    LiveData<List<ListItem>> getListItems();

    @Query("UPDATE list_table SET completed = '1' WHERE id = :theId ")
    void check(int theId);

    @Query("UPDATE list_table SET completed = '0' WHERE id = :theId ")
    void uncheck(int theId);

    @Query("DELETE FROM list_table WHERE id = :theId")
    void delete(int theId);


}
