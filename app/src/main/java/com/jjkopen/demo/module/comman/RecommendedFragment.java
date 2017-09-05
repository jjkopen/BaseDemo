package com.jjkopen.demo.module.comman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjkopen.demo.R;
import com.jjkopen.demo.greendao.bean.Shop;
import com.jjkopen.demo.greendao.dao.CartDao;
import com.jjkopen.demo.module.comman.adapter.EmptyAdapter;
import com.jjkopen.demo.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2017/8/16.
 */

public class RecommendedFragment extends Fragment {
    private View noDataView;
    private TextView btn_refresh;

    @BindView(R.id.listview)
    EmptyRecyclerView listview;
    @BindView(R.id.swipelayout)
    SwipeRefreshLayout swipelayout;

    private EmptyAdapter adapter;
    private List<Shop> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        noDataView = LayoutInflater.from(getActivity()).inflate(R.layout.view_nodata, null);
        btn_refresh = noDataView.findViewById(R.id.btn_refresh);
        btn_refresh.setOnClickListener(view -> initData());

        initView();
        initData();
    }

    private void initView() {
        noDataView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((ViewGroup) listview.getParent()).addView(noDataView);
        listview.setEmptyView(noDataView);
        list = new ArrayList<>();
        adapter = new EmptyAdapter(getActivity(), list);
        listview.setLayoutManager(new LinearLayoutManager(getActivity()));
        listview.setAdapter(adapter);
        swipelayout.setOnRefreshListener(() -> {
            updateData();
            swipelayout.setRefreshing(false);
        });
    }

    private void initData() {
        list.clear();
        for (int i = 0; i < 5; i++) {
            Shop shop = new Shop();
            shop.setName("shop" + i);
            CartDao.insertCart(shop);
        }
        queryData();
    }

    private void queryData() {
        list.clear();
        list.addAll(CartDao.queryAll());
        adapter.notifyDataSetChanged();
    }

    private void updateData() {
        if (!list.isEmpty()) {
            Shop shop = list.get(new Random().nextInt(5));
            shop.setName("Change!");
            CartDao.updateCart(shop);
            queryData();
        }
    }

}