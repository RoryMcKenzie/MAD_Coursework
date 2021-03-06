package com.example.coursework;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ListItemViewModel extends AndroidViewModel {

    private ListItemRepository mRepository;

    private LiveData<List<ListItem>> mAllItems;
    private LiveData<List<ListItem>> mAllItemsPriority;


    public ListItemViewModel (Application application) {
        super(application);
        mRepository = new ListItemRepository(application);
        mAllItems = mRepository.getAllListItems();
        mAllItemsPriority = mRepository.getAllListItemsPriority();
    }

    LiveData<List<ListItem>> getAllItems() { return mAllItems; }
    LiveData<List<ListItem>> getAllItemsPriority() { return mAllItemsPriority; }


    public void insert(ListItem item) { mRepository.insert(item); }

    public void deleteAll() {mRepository.deleteAll();}

    public void check(int id) {mRepository.check(id);}

    public void uncheck(int id) {mRepository.uncheck(id);}

    public void delete(int id) {mRepository.delete(id);}

}