package com.jjkopen.demo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private MyApp application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
        ButterKnife.bind(this);
        application = (MyApp) getApplication();

        initActionBar();
        initView();
        initData();

        // log日志与Toast通过工具类实现
    }

    protected abstract void bindViews();// 绑定视图

    protected abstract void initView();// 初始化控件

    protected abstract void initData();// 初始化数据

    protected abstract void initActionBar();// 初始化导航栏

    /**
     * 隐藏输入法
     */
    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
