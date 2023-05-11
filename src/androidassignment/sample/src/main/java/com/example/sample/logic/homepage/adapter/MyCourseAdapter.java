package com.example.sample.logic.homepage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.CourseBean;
import com.example.sample.mvvm.ItemClickCallBack;

import java.util.ArrayList;
import java.util.List;

public class MyCourseAdapter extends RecyclerView.Adapter<MyCourseAdapter.ViewHolder> {

    ItemClickCallBack<CourseBean> callBack;

    public MyCourseAdapter(ItemClickCallBack<CourseBean> i) {
        callBack = i;
    }

    public void refreshData(List<CourseBean> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    List<CourseBean> dataList = new ArrayList<>();

    @NonNull
    @Override
    public MyCourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_mycourse, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCourseAdapter.ViewHolder holder, int position) {
        CourseBean courseBean = dataList.get(position);
        holder.TvCourseName.setText(courseBean.getCourseName());
        holder.TvTeacher.setText(courseBean.getTeacherName());
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
        TextView TvCourseName;
        TextView TvTeacher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TvCourseName = itemView.findViewById(R.id.tv_course);
            TvTeacher = itemView.findViewById(R.id.tv_teacher);
        }
    }
}
