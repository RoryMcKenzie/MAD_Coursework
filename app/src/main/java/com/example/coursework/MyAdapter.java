package com.example.coursework;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //Stores the id of the record for each ViewHolder
    //Currently not used
    public static int recordId;

    private static ListItemViewModel mListItemViewModel;


    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView subtitle;
        public CheckBox checkbox;
        public TextView id;

        //Constructor
        public MyViewHolder(View v) {
            super(v);

            title = itemView.findViewById(R.id.item_title);
            subtitle = itemView.findViewById(R.id.item_subtitle);
            checkbox = itemView.findViewById(R.id.item_checkbox);
            id = itemView.findViewById(R.id.item_id);



            //Strikes through text if checkbox checked
            checkbox.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(checkbox.isChecked()){
                        // This works, but relies on a TextView I'd rather not exist (id)
                        // Also pretty sure this is bad programming, would like to find a better way
                        //But this'll do for now
                        mListItemViewModel.check(Integer.parseInt(id.getText().toString()));
                        title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        subtitle.setPaintFlags(subtitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        mListItemViewModel.uncheck(Integer.parseInt(id.getText().toString()));
                        title.setPaintFlags(title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        subtitle.setPaintFlags(subtitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });
        }
    }

    //Create inflater
    private final LayoutInflater mInflater;
    //Create list of all items in to-do list
    private List<ListItem> mItems;

    //Constructor for dataset
    public MyAdapter(Context context, ListItemViewModel MListItemViewModel){
        mInflater = LayoutInflater.from(context);
        this.mListItemViewModel = MListItemViewModel;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate the custom layout
        View toDoView = mInflater.inflate(R.layout.my_text_view, parent, false);

        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(toDoView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItem current = mItems.get(position);
        holder.title.setText(current.getMTitle());
        holder.subtitle.setText(current.getMNote());
        holder.checkbox.setChecked(current.getMCompleted());
        recordId = current.getMId();
        holder.id.setText(Integer.toString(recordId));
    }

    // Update the cached copy of the items in the adapter
    void setListItems(List<ListItem> listItems){
        mItems = listItems;
        notifyDataSetChanged();
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(mItems != null){
            return mItems.size();
        } else return 0;
    }


}
