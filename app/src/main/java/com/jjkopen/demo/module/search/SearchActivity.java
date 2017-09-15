package com.jjkopen.demo.module.search;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjkopen.demo.R;
import com.jjkopen.demo.base.BaseActivity;
import com.jjkopen.demo.greendao.bean.SearchHistory;
import com.jjkopen.demo.greendao.dao.SearchHistoryDao;
import com.jjkopen.demo.module.comman.CloseActivity;
import com.jjkopen.demo.module.search.adapter.SearchAdapter;
import com.jjkopen.demo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/8/30.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.btn_searchdelete)
    ImageView btnSearchdelete;
    @BindView(R.id.tv_recommandsearch)
    TextView tvRecommandsearch;
    @BindView(R.id.recommandsearch)
    CardView recommandSearch;
    @BindView(R.id.listview_search_history)
    RecyclerView listview;
    @BindView(R.id.btn_clearall)
    TextView btnClearall;

    private final int DELETEONE = 0X001;
    private final int DELETEALL = 0X002;

    private SearchAdapter adapter;
    private List<SearchHistory> list;

    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        tvRecommandsearch.setText(getIntent().getStringExtra("recommandsearch"));
        recommandSearch.setOnClickListener(view -> {
            editSearch.setText(tvRecommandsearch.getText());
            insertHistory();
        });
        list = new ArrayList<>();
        adapter = new SearchAdapter(this, list);
        listview.setLayoutManager(new LinearLayoutManager(this));
        listview.setAdapter(adapter);
        adapter.setOnDeleteListener(position -> deleteHistory(DELETEONE, position));
        adapter.setOnItemClickListener(position -> {
            editSearch.setText(list.get(position).getName());
            insertHistory();
        });
        btnClearall.setOnClickListener(view -> showDeleteDialog());
        queryHistory();
    }

    @Override
    protected void initActionBar() {
        btnBack.setOnClickListener(view -> finish());
        editSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH && !TextUtils.isEmpty(editSearch.getText().toString().trim())) {
                ToastUtils.shortToast(editSearch.getText().toString().trim());
                insertHistory();
            }
            return true;
        });
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 0) {
                    btnSearchdelete.setVisibility(View.VISIBLE);
                } else {
                    btnSearchdelete.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btnSearchdelete.setOnClickListener(view -> editSearch.setText(""));
    }

    private void showDeleteDialog() {
        new AlertDialog.Builder(this)
                .setMessage("确认清除所有搜索记录吗?")
                .setPositiveButton("确定", (dialogInterface, i) -> deleteHistory(DELETEALL, 1))
                .setNegativeButton("取消", (dialogInterface, i) -> startActivity(new Intent(this, CloseActivity.class)))
                .setCancelable(true)
                .create().show();
    }

    private void insertHistory() {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setName(editSearch.getText().toString().trim());
        SearchHistoryDao.insertHistory(searchHistory);
        queryHistory();
    }

    private void deleteHistory(int TAG, int position) {
        if (TAG == DELETEONE)
            SearchHistoryDao.deleteHistory(list.get(position));
        else if (TAG == DELETEALL)
            SearchHistoryDao.clearAll();
        queryHistory();
    }

    private void queryHistory() {
        list.clear();
        list.addAll(SearchHistoryDao.queryAll());
        Collections.reverse(list);
        adapter.notifyDataSetChanged();
    }
}
