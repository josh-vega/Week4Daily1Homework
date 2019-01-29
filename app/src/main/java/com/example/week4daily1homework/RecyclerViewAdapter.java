package com.example.week4daily1homework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week4daily1homework.model.repo.Item;
import com.example.week4daily1homework.model.repo.RepoResponse;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Item> list = new ArrayList<>();

    public RecyclerViewAdapter(ArrayList<Item> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Item response = list.get(position);

        if(response != null) {
            String field1 = response.getFullName();
            String field2 = response.getOwner().getLogin();
            String field3 = response.getOwner().getReposUrl();

            viewHolder.setItemRepo(response);
            viewHolder.tvField1.setText("Full Name: " + field1);
            viewHolder.tvField2.setText("Login: " + field2);
            viewHolder.tvField3.setText("RepoUrl: " + field3);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Item itemRepo;
        TextView tvField1, tvField2, tvField3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvField1 = itemView.findViewById(R.id.tvField1);
            tvField2 = itemView.findViewById(R.id.tvField2);
            tvField3 = itemView.findViewById(R.id.tvField3);
        }

        public void setItemRepo(Item itemRepo){
            this.itemRepo = itemRepo;
        }

    }
}
