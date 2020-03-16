package com.example.coursework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static ListItemViewModel mListItemViewModel;
    private Context context;

    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView subtitle;
        public CheckBox checkbox;
        public TextView id;
        public Button delete;
        public TextView priority;

        //Constructor
        public MyViewHolder(View v) {
            super(v);

            title = itemView.findViewById(R.id.item_title);
            subtitle = itemView.findViewById(R.id.item_subtitle);
            checkbox = itemView.findViewById(R.id.item_checkbox);
            id = itemView.findViewById(R.id.item_id);
            priority = itemView.findViewById(R.id.item_priority);
            delete = itemView.findViewById(R.id.button_delete);

            //Updates record in database when checkbox clicked
            checkbox.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(checkbox.isChecked()){
                        mListItemViewModel.check(Integer.parseInt(id.getText().toString()));
                    } else {
                        mListItemViewModel.uncheck(Integer.parseInt(id.getText().toString()));
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    mListItemViewModel.delete(Integer.parseInt(id.getText().toString()));
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
        this.context = context;
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
        holder.id.setText(Integer.toString(current.getMId()));
        switch (current.getMPriority()){
            case 3: holder.priority.setText("Priority: \nHigh");
                    holder.priority.setTextColor(context.getResources().getColor(R.color.highPriority));
                break;
            case 2: holder.priority.setText("Priority: \nMedium");
                    holder.priority.setTextColor(context.getResources().getColor(R.color.mediumPriority));
                break;
            case 1: holder.priority.setText("Priority: \nLow");
                    holder.priority.setTextColor(context.getResources().getColor(R.color.lowPriority));
                break;
            default: holder.priority.setText("Priority: \nNone");
                break;
        }
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
