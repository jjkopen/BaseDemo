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

import com.bumptech.glide.Glide;
import com.jjkopen.demo.R;
import com.jjkopen.demo.greendao.bean.Shop;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2017/8/28.
 */

class ShoppingCarStoreAdapter extends RecyclerView.Adapter<ShoppingCarStoreAdapter.ViewHolder> {
    private Context context;
    private List<Shop> list;

    public ShoppingCarStoreAdapter(Context context, List<Shop> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_goods_shoppingcar, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textName.setText("90分ultra smart跑鞋 智能版(含芯片)男款 冲浪蓝 40");
        holder.textPrice.setText(10 + "");
        holder.textTotalPrice.setText(Float.parseFloat(holder.textCount.getText().toString().trim()) * 10 + "");
        Glide.with(context).load(list.get(position).getImage_url()).into(holder.imgGoods);
//        holder.btnMinus.setOnClickListener(view -> count--);
//        holder.btnPlus.setOnClickListener(view -> count++);
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
        @BindView(R.id.img_goods)
        ImageView imgGoods;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_total_price)
        TextView textTotalPrice;
        @BindView(R.id.text_price)
        TextView textPrice;
        @BindView(R.id.btn_minus)
        TextView btnMinus;
        @BindView(R.id.text_count)
        TextView textCount;
        @BindView(R.id.btn_plus)
        TextView btnPlus;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
