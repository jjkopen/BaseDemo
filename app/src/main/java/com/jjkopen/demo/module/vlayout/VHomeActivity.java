package com.jjkopen.demo.module.vlayout;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.DefaultLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.jjkopen.demo.R;
import com.jjkopen.demo.base.BaseActivity;
import com.jjkopen.demo.widget.EmptyRecyclerView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VHomeActivity extends BaseActivity {
    private View noDataView, noNetView;
    private TextView btn_refresh;

    @BindView(R.id.appbar_title)
    TextView appbarTitle;
    @BindView(R.id.swipelayout)
    SwipeRefreshLayout swipelayout;
    @BindView(R.id.recyclerview)
    EmptyRecyclerView recyclerView;

    private VirtualLayoutManager layoutManager;
    private List<LayoutHelper> helpers;

    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_vhome);
        noDataView = getLayoutInflater().inflate(R.layout.view_nodata, null);
        btn_refresh = noDataView.findViewById(R.id.btn_refresh);
        noNetView = getLayoutInflater().inflate(R.layout.view_nonet, null);
        btn_refresh.setOnClickListener(view -> initData());
    }

    @Override
    protected void initView() {
        swipelayout.setOnRefreshListener(() -> {
            helpers.clear();
            layoutManager.setLayoutHelpers(helpers);
            recyclerView.getAdapter().notifyDataSetChanged();
            swipelayout.setRefreshing(false);
        });

        noDataView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((ViewGroup) recyclerView.getParent()).addView(noDataView);
        recyclerView.setEmptyView(noDataView);
        layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
//        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
//        recyclerView.setAdapter(delegateAdapter);
        helpers = new LinkedList<>();
        recyclerView.setAdapter(new VirtualLayoutAdapter(layoutManager) {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(VHomeActivity.this).inflate(R.layout.item_vhome_recycler, parent, false);
                return new VHOMEVHolder(v);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                VHOMEVHolder vhomevHolder = (VHOMEVHolder) holder;
                vhomevHolder.tvName.setText("Name: " + position);
                vhomevHolder.tvPrice.setText("Price: ¥ " + (100 * position));
                vhomevHolder.ivImg.setImageResource(R.drawable.bili_default_image_tv);
            }

            @Override
            public int getItemCount() {
                List<LayoutHelper> helpers = getLayoutHelpers();
                if (helpers == null) {
                    return 0;
                }
                int count = 0;
                for (int i = 0, size = helpers.size(); i < size; i++) {
                    count += helpers.get(i).getItemCount();
                }
                return count;
            }
        });
    }

    @Override
    protected void initData() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setItemCount(21);

        helpers.add(DefaultLayoutHelper.newHelper(9));
        helpers.add(new StickyLayoutHelper(true));
//        helpers.add(new ScrollFixLayoutHelper(FixLayoutHelper.TOP_RIGHT, -200, 50));
        helpers.add(gridLayoutHelper);
        helpers.add(new StickyLayoutHelper(false));
        layoutManager.setLayoutHelpers(helpers);

        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void initActionBar() {
        appbarTitle.setText("V-Layout");
    }

    @OnClick({R.id.appbar_back})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.appbar_back:
                finish();
                break;
        }
    }

    class VHOMEVHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.iv_img)
        ImageView ivImg;

        public VHOMEVHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}