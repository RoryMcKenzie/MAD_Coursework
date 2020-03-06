package com.example.coursework;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class ListItemRepository {

    private ListItemDao mListItemDao;
    private LiveData<List<ListItem>> mAllListItems;

    ListItemRepository(Application application) {
        ListItemRoomDatabase db = ListItemRoomDatabase.getDatabase(application);
        mListItemDao = db.listItemDao();
        mAllListItems = mListItemDao.getListItems();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<ListItem>> getAllListItems() {
        return mAllListItems;
    }

    //Must be called on non-UI thread or throws exception
    void insert(ListItem listItem) {
        ListItemRoomDatabase.databaseWriteExecutor.execute(() -> {
            mListItemDao.insert(listItem);
        });
    }

    //Must be called on non-UI thread or throws exception
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
}