package com.jjkopen.demo.module.shop;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjkopen.demo.R;
import com.jjkopen.demo.base.BaseActivity;
import com.jjkopen.demo.greendao.bean.Shop;
import com.jjkopen.demo.module.comman.adapter.EmptyAdapter;
import com.jjkopen.demo.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2017/8/28.
 */

public class ShoppingCarActivity extends BaseActivity {
    private View noDataView;

    @BindView(R.id.appbar_title)
    TextView appbarTitle;
    @BindView(R.id.listview)
    EmptyRecyclerView listview;
    @BindView(R.id.swipelayout)
    SwipeRefreshLayout swipelayout;

    private EmptyAdapter adapter;
    private List<Shop> list;
    
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_shoppingcar);
        noDataView = LayoutInflater.from(this).inflate(R.layout.view_nodata, null);
    }

    @Override
    protected void initView() {
        noDataView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((ViewGroup) listview.getParent()).addView(noDataView);
        listview.setEmptyView(noDataView);
        list = new ArrayList<>();
        adapter = new EmptyAdapter(this, list);
        listview.setLayoutManager(new LinearLayoutManager(this));
        listview.setAdapter(adapter);
        swipelayout.setOnRefreshListener(() -> {
            swipelayout.setRefreshing(false);
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initActionBar() {
        appbarTitle.setText("购物车");
    }

    @OnClick({R.id.appbar_back})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.appbar_back:
                finish();
                break;
        }
    }
}
