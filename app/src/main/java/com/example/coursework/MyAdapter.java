package com.example.coursework;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;

    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView subtitle;
        public CheckBox checkbox;

        public MyViewHolder(View v) {
            super(v);

            title = itemView.findViewById(R.id.item_title);
            subtitle = itemView.findViewById(R.id.item_subtitle);
            checkbox = itemView.findViewById(R.id.item_checkbox);
           //Strikes through text if checkbox checked
            checkbox.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(checkbox.isChecked()){
                        title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        subtitle.setPaintFlags(subtitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        title.setPaintFlags(title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        subtitle.setPaintFlags(subtitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });
        }
    }

    //Constructor for dataset
    public MyAdapter(ArrayList<String> myDataset){
    mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View toDoView = inflater.inflate(R.layout.my_text_view, parent, false);

        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(toDoView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position));
        holder.subtitle.setText("Subtitle");
        holder.checkbox.setChecked(false);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
