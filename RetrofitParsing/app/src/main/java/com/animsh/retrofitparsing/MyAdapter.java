package com.animsh.retrofitparsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    List<DataModel> list;
    Context context;

    public MyAdapter(List<DataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView id, title, userId, completed;

        public VH(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.todo_id);
            userId = itemView.findViewById(R.id.todo_user_id);
            title = itemView.findViewById(R.id.todo_title);
            completed = itemView.findViewById(R.id.todo_completed);
        }

        public void setData(DataModel dataModel) {
            id.setText(String.valueOf("ID: " + dataModel.getId()));
            userId.setText(String.valueOf("UserID: " + dataModel.getUserId()));
            title.setText(String.format("Title: %s", dataModel.getTitle()));
            completed.setText(String.valueOf("Completed: " + dataModel.isCompleted()));
        }
    }
}
