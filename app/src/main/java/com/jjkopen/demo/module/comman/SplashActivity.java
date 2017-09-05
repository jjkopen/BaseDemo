package com.jjkopen.demo.module.comman;

import android.content.Intent;
import android.widget.ImageView;

import com.jjkopen.demo.R;
import com.jjkopen.demo.base.BaseActivity;
import com.jjkopen.demo.eventbus.SplashEvent;
import com.jjkopen.demo.widget.ImageAnimation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_loading)
    ImageView mIvLoading;
    private ImageAnimation animation;

    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_splash);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        animation = new ImageAnimation(mIvLoading, 40);
    }

    @Override
    protected void initActionBar() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onSplashEvent(SplashEvent event) {
        if (event.isFinish()) {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animation.cancel();
        EventBus.getDefault().unregister(this);
    }
}
