package com.example.coursework;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ListItemViewModel extends AndroidViewModel {

    private ListItemRepository mRepository;

    private LiveData<List<ListItem>> mAllItems;

    public ListItemViewModel (Application application) {
        super(application);
        mRepository = new ListItemRepository(application);
        mAllItems = mRepository.getAllListItems();
    }

    LiveData<List<ListItem>> getAllItems() { return mAllItems; }

    public void insert(ListItem item) { mRepository.insert(item); }
}