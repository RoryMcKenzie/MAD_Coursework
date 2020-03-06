package com.example.coursework;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "list_table")
class ListItem {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int mId;

    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo (name = "note")
    private String mNote;

    @NonNull
    @ColumnInfo(name = "completed")
    private Boolean mCompleted;

    public ListItem( @NonNull String title, String note, @NonNull Boolean completed){
        this.mTitle = title;
        this.mNote = note;
        this.mCompleted = completed;
    }

    public ListItem getListItem(){
        ListItem temp = new ListItem(this.getMTitle(), this.getMNote(), this.getMCompleted());
        return temp;
    }

    public int getMId() {
        return this.mId;
    }

    public void setMId(int id){

        this.mId = id;
    }

    public String getMTitle() {
        return this.mTitle;
    }

    public String getMNote() {
        return this.mNote;
    }

    public Boolean getMCompleted(){
        return this.mCompleted;
    }

    public void setMCompleted(Boolean completed) {
        this.mCompleted = completed;
    }
}
