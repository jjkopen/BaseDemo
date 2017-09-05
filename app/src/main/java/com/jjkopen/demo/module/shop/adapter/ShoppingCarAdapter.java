package com.jjkopen.demo.module.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjkopen.demo.R;
import com.jjkopen.demo.greendao.bean.Shop;
import com.jjkopen.demo.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2017/8/28.
 */

public class ShoppingCarAdapter extends RecyclerView.Adapter<ShoppingCarAdapter.ViewHolder> {
    private Context context;
    private List<Shop> list;
    private ShoppingCarStoreAdapter adapter;

    public ShoppingCarAdapter(Context context, List<Shop> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_store_shoppingcar, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.store.setText(list.get(position).getName());
//        holder.check.setChecked();
        holder.llCheck.setOnClickListener(view -> {
            //todo 勾选状态
        });
        holder.chevronRight.setOnClickListener(view -> ToastUtils.shortToast("GO STORE!"));
        adapter = new ShoppingCarStoreAdapter(context, list);
        holder.listview.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.check)
        CheckedTextView check;
        @BindView(R.id.ll_check)
        LinearLayout llCheck;
        @BindView(R.id.store)
        TextView store;
        @BindView(R.id.chevron_right)
        ImageView chevronRight;
        @BindView(R.id.listview)
        RecyclerView listview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
