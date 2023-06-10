package com.example.mazeapp.MazeList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.mazeapp.Data.MazeListItem;
import com.example.mazeapp.R;

public class MazeListAdapter extends RecyclerView.Adapter<MazeListAdapter.ViewHolder> {

    private List<MazeListItem> itemList;
    private final View.OnClickListener clickListener;

    public MazeListAdapter(View.OnClickListener listener) {
        itemList = new ArrayList<MazeListItem>();
        clickListener = listener;
    }

    public void addItem(MazeListItem item){
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<MazeListItem> items){
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(List<MazeListItem> items){
        itemList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maze_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setTag(itemList.get(position));
        holder.itemView.setOnClickListener(clickListener);

        holder.mazeListItemView.setText(itemList.get(position).toString());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mazeListItemView;

        ViewHolder(View view) {
            super(view);
            mazeListItemView = view.findViewById(R.id.maze_list_item);
        }
    }
}
