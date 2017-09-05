package com.jjkopen.demo.module.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjkopen.demo.R;
import com.jjkopen.demo.greendao.bean.SearchHistory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2017/8/30.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<SearchHistory> list;

    public SearchAdapter(Context context, List<SearchHistory> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search_history, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvRecommandsearch.setText(list.get(position).getName());
        holder.btnDelete.setOnClickListener(view -> deleteListener.OnDelete(position));
        holder.itemView.setOnClickListener(view -> itemClickListener.OnItemClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_recommandsearch)
        TextView tvRecommandsearch;
        @BindView(R.id.btn_delete)
        ImageView btnDelete;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnDeleteListener deleteListener;
    private OnItemClickListener itemClickListener;

    public interface OnDeleteListener {
        void OnDelete(int position);
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnDeleteListener(OnDeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
