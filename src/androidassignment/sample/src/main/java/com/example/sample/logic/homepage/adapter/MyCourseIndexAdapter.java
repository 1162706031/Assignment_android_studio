package com.example.sample.logic.homepage.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.mvvm.ItemClickCallBack;

import java.util.ArrayList;
import java.util.List;

public class MyCourseIndexAdapter extends RecyclerView.Adapter<MyCourseIndexAdapter.ViewHolder> {

    ItemClickCallBack<String> callBack;

    int selectedPos = 0;

    public MyCourseIndexAdapter(ItemClickCallBack<String> i) {
        callBack = i;
    }

    public void refreshData(List<String> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    List<String> dataList = new ArrayList<>();

    @NonNull
    @Override
    public MyCourseIndexAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_index, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCourseIndexAdapter.ViewHolder holder, int position) {
        holder.TvCatalog.setText(dataList.get(position));
        holder.TvCatalog.setTypeface(position == selectedPos ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = holder.getAdapterPosition();
                notifyDataSetChanged();
                callBack.onItemClick(selectedPos, holder.TvCatalog.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TvCatalog;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TvCatalog = itemView.findViewById(R.id.tv_catalog);
        }
    }
}
