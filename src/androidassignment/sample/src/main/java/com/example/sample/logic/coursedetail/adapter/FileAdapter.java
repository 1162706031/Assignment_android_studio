package com.example.sample.logic.coursedetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.CourseFileBean;
import com.example.sample.mvvm.ItemClickCallBack;

import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    ItemClickCallBack<CourseFileBean> callBack;

    public FileAdapter(ItemClickCallBack<CourseFileBean> i) {
        callBack = i;
    }

    public void refreshData(List<CourseFileBean> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    List<CourseFileBean> dataList = new ArrayList<>();

    @NonNull
    @Override
    public FileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_coursefile, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FileAdapter.ViewHolder holder, int position) {
        CourseFileBean courseBean = dataList.get(position);
        holder.TvName.setText(courseBean.getFileName());
        holder.TvType.setText(courseBean.getFileType() + "");
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
        TextView TvType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TvName = itemView.findViewById(R.id.tv_file);
            TvType = itemView.findViewById(R.id.tv_type);
        }
    }
}
