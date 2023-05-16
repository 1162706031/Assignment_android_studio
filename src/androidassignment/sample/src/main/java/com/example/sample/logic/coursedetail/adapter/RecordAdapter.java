package com.example.sample.logic.coursedetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.CourseRecordBean;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    public void refreshData(List<CourseRecordBean> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    List<CourseRecordBean> dataList = new ArrayList<>();

    @NonNull
    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_record, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {
        CourseRecordBean courseBean = dataList.get(position);
        holder.TvName.setText(courseBean.getUserName());
        holder.TvType.setText(courseBean.getRecordType() + "");
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
            TvName = itemView.findViewById(R.id.tv_name);
            TvType = itemView.findViewById(R.id.tv_type);
        }
    }
}
