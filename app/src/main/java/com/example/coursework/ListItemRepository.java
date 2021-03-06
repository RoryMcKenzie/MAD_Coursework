package com.example.coursework;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class ListItemRepository {

    private ListItemDao mListItemDao;
    private LiveData<List<ListItem>> mAllListItems;
    private LiveData<List<ListItem>> mAllListItemsPriority;


    ListItemRepository(Application application) {
        ListItemRoomDatabase db = ListItemRoomDatabase.getDatabase(application);
        mListItemDao = db.listItemDao();
        mAllListItems = mListItemDao.getListItems();
        mAllListItemsPriority = mListItemDao.getListItemsPriority();

    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<ListItem>> getAllListItems() {
        return mAllListItems;
    }
    LiveData<List<ListItem>> getAllListItemsPriority() {
        return mAllListItemsPriority;
    }


    //All methods must be called on non-UI thread or throws exception
    void insert(ListItem listItem) {
        ListItemRoomDatabase.databaseWriteExecutor.execute(() -> {
            mListItemDao.insert(listItem);
        });
    }

    void deleteAll() {
        ListItemRoomDatabase.databaseWriteExecutor.execute(() -> {
            mListItemDao.deleteAll();
        });
    }

    void check(int id){
        ListItemRoomDatabase.databaseWriteExecutor.execute(() -> {
            mListItemDao.check(id);
        });
    }

    void uncheck(int id){
        ListItemRoomDatabase.databaseWriteExecutor.execute(() -> {
            mListItemDao.uncheck(id);
        });
    }

    void delete(int id){
        ListItemRoomDatabase.databaseWriteExecutor.execute(() -> {
            mListItemDao.delete(id);
        });
    }
}