package com.example.du5fd.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Du5fd on 25-4-2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder>  {

    private ArrayList<String> mDataset;
    private SparseBooleanArray selectedItems;

    public MyAdapter(ArrayList<String> myDataset){
        mDataset = myDataset;
        selectedItems = new SparseBooleanArray();
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        //final String name = mDataset.get(position);
        holder.txtHeader.setText(mDataset.get(position));
        holder.txtFooter.setText("Footer: " + mDataset.get(position));
        holder.itemView.setActivated(selectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void toggleSelection(int pos){
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections(){
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount(){
        return selectedItems.size();
    }

    public ArrayList<Integer> getSelectedItems() {
        ArrayList<Integer> items =
                new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    public final static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;

        public ItemViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public MyAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        //create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

}
