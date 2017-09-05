package com.jjkopen.demo.module.comman.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjkopen.demo.R;
import com.jjkopen.demo.greendao.bean.Shop;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2017/8/28.
 */

public class EmptyAdapter extends RecyclerView.Adapter<EmptyAdapter.EmptyHolder> {
    private Context context;
    private List<Shop> list;

    public EmptyAdapter(Context context, List<Shop> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public EmptyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EmptyHolder(LayoutInflater.from(context).inflate(R.layout.item_tv, parent, false));
    }

    @Override
    public void onBindViewHolder(EmptyHolder holder, int position) {
        holder.tv.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class EmptyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id_num)
        TextView tv;

        public EmptyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
