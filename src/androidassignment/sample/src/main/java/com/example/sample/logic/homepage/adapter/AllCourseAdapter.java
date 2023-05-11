package com.example.sample.logic.homepage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.CourseBean;
import com.example.sample.mvvm.ItemClickCallBack;
import com.example.sample.utils.image.impls.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

public class AllCourseAdapter extends RecyclerView.Adapter<AllCourseAdapter.ViewHolder> {

    ItemClickCallBack<CourseBean> callBack;

    public AllCourseAdapter(ItemClickCallBack<CourseBean> i) {
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
    public AllCourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_allcourse, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCourseAdapter.ViewHolder holder, int position) {
        CourseBean courseBean = dataList.get(position);
        holder.TvCourseName.setText(courseBean.getCourseName());
        holder.TvTeacher.setText(courseBean.getTeacherName());
        GlideImageLoader.load(holder.IvCover, courseBean.getCoverUrl());
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
        ImageView IvCover;
        TextView TvCourseName;
        TextView TvTeacher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IvCover = itemView.findViewById(R.id.iv_cover);
            TvCourseName = itemView.findViewById(R.id.tv_course);
            TvTeacher = itemView.findViewById(R.id.tv_teacher);
        }
    }
}
