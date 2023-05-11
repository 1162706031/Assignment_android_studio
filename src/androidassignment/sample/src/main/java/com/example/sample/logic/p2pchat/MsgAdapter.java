package com.example.sample.logic.p2pchat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.MsgBean;
import com.example.sample.utils.image.impls.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    public void refreshData(List<MsgBean> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    List<MsgBean> dataList = new ArrayList<>();

    @NonNull
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_msg, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder holder, int position) {
        MsgBean msg = dataList.get(position);
        boolean isLeft = position % 2 == 0;
        if (isLeft) {//对方
            GlideImageLoader.load(holder.IvHead, msg.getUserHeadUrl());
            holder.TvContent.setText(msg.getText());
        } else {
            GlideImageLoader.load(holder.IvHeadR, msg.getUserHeadUrl());
            holder.TvContentR.setText(msg.getText());
        }
        holder.TvContent.setVisibility(isLeft ? View.VISIBLE : View.GONE);
        holder.IvHead.setVisibility(isLeft ? View.VISIBLE : View.GONE);
        holder.IvHeadR.setVisibility(isLeft ? View.GONE : View.VISIBLE);
        holder.TvContentR.setVisibility(isLeft ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView IvHead;
        TextView TvContent;
        ImageView IvHeadR;
        TextView TvContentR;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IvHead = itemView.findViewById(R.id.iv_head);
            IvHeadR = itemView.findViewById(R.id.iv_head_r);
            TvContent = itemView.findViewById(R.id.tv_content);
            TvContentR = itemView.findViewById(R.id.tv_content_r);
        }
    }
}
