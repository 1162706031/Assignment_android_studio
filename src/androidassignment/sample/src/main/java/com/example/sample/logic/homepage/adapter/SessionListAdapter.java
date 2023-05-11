package com.example.sample.logic.homepage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.SessionBean;
import com.example.sample.mvvm.ItemClickCallBack;

import java.util.ArrayList;
import java.util.List;

public class SessionListAdapter extends RecyclerView.Adapter<SessionListAdapter.ViewHolder> {

    ItemClickCallBack<SessionBean> callBack;

    public SessionListAdapter(ItemClickCallBack<SessionBean> i) {
        callBack = i;
    }

    public void refreshData(List<SessionBean> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    List<SessionBean> dataList = new ArrayList<>();

    @NonNull
    @Override
    public SessionListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_session, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionListAdapter.ViewHolder holder, int position) {
        SessionBean sessionBean = dataList.get(position);
        holder.TvName.setText(sessionBean.getSesionName());
        holder.TvLastMsg.setText(sessionBean.getLastMsg());
        holder.TvTime.setText(sessionBean.getUpdateTime());
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
        TextView TvLastMsg;
        TextView TvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TvName = itemView.findViewById(R.id.tv_name);
            TvLastMsg = itemView.findViewById(R.id.tv_last_msg);
            TvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
