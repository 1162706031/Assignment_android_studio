package com.example.sample.logic.homepage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.ClassMates;
import com.example.sample.mvvm.ItemClickCallBack;

import java.util.ArrayList;
import java.util.List;

public class ClassMatesAdapter extends RecyclerView.Adapter<ClassMatesAdapter.ViewHolder> {

    ItemClickCallBack<ClassMates> callBack;

    public ClassMatesAdapter(ItemClickCallBack<ClassMates> i) {
        callBack = i;
    }

    public void refreshData(List<ClassMates> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    List<ClassMates> dataList = new ArrayList<>();

    @NonNull
    @Override
    public ClassMatesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_classmate, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassMatesAdapter.ViewHolder holder, int position) {
        ClassMates bean = dataList.get(position);
        holder.TvName.setText(bean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedPos = holder.getAdapterPosition();
                callBack.onItemClick(selectedPos, dataList.get(selectedPos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
