package com.jjkopen.demo.module.comman;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.jjkopen.demo.R;
import com.jjkopen.demo.base.BaseActivity;
import com.jjkopen.demo.module.search.SearchActivity;
import com.jjkopen.demo.module.vlayout.VHomeActivity;
import com.jjkopen.demo.utils.ConstantUtil;
import com.jjkopen.demo.utils.PreferenceUtils;
import com.jjkopen.demo.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends BaseActivity {
    @BindView(R.id.appbarlayout)
    AppBarLayout mAppbarlayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_drawer)
    ImageView mToolbarDrawer;
    @BindView(R.id.toolbar_head)
    CircleImageView mToolbarHead;
    @BindView(R.id.toolbar_name)
    TextView mToolbarName;
    @BindView(R.id.tablayout)
    SlidingTabLayout mTablayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.nav_View)
    NavigationView mNavigationView;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;
    private ArrayList<Fragment> fragmentList;
    private String[] str;

    private Vibrator mVibrator;  //声明一个振动器对象

    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
        initDrawerlayout();
        initToolbar();
        initTablayout();
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_download:
                ToastUtils.showToastContinue("Duang!");
                startActivity(new Intent(this, VHomeActivity.class));
                break;
            case R.id.action_search:
                ToastUtils.showToastContinue("Xiu!");
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra("recommandsearch","推荐搜索内容");
                startActivity(intent);
                break;
        }
        return true;
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        mToolbarHead.setImageResource(R.drawable.bili_default_avatar);
//        mToolbarDrawer.setOnClickListener(view -> mDrawerLayout.openDrawer(Gravity.START));
        mToolbarName.setText("让记忆有时去追");
        setSupportActionBar(mToolbar);
        mToolbar.setOnClickListener(view -> mDrawerLayout.openDrawer(Gravity.START));
    }

    private void initDrawerlayout() {
//        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.getChildAt(0).setVerticalScrollBarEnabled(false);

        View headView = mNavigationView.getHeaderView(0);
        headView.findViewById(R.id.iv_bg).setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                view.setSelected(true);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.performClick();//必须写v.performClick(),解决与单击事件的冲突
                view.setSelected(false);
            }
            return true;
        });
        headView.findViewById(R.id.iv_head).setOnClickListener(view -> switchNightMode());
        headView.findViewById(R.id.tv_user).setOnClickListener(view -> switchNightMode());
        headView.findViewById(R.id.tv_coin).setOnClickListener(view -> switchNightMode());
        headView.findViewById(R.id.tv_B_coin).setOnClickListener(view -> switchNightMode());
        headView.findViewById(R.id.iv_notification).setOnClickListener(view -> switchNightMode());

        mNavigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_feedback:
                case R.id.nav_personal_info:
                case R.id.nav_system_setting:
                case R.id.nav_like:
                    ToastUtils.showToastContinue(item.getItemId() + "");
                    break;
                case R.id.nav_boom:
                    mVibrator.vibrate(new long[]{0, 2000, 0, 0}, -1);
                    break;
            }
            return false;
        });
    }

    private void initTablayout() {
        str = new String[]{"直播", "推荐", "追番", "分区", "动态"};
        fragmentList = new ArrayList<>();
        fragmentList.add(new MainFragment());
        fragmentList.add(new RecommendedFragment());
        fragmentList.add(new MangaFragment());
        fragmentList.add(new MineFragment());
        fragmentList.add(new MineFragment());

        mTablayout.setIndicatorWidthEqualTitle(true);
        mTablayout.setTabSpaceEqual(true);
        //在setvp之前设置属性,否则有的属性不生效
        mTablayout.setViewPager(mViewPager, str, this, fragmentList);
    }

    long lastTime = 0;

    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawers();
        } else if (now - lastTime >= 1000 * 2) {
            lastTime = now;
            ToastUtils.showToastContinue("连续点击将退出程序");
        } else {
            finish();
        }
    }

    /**
     * 日夜间模式切换
     */
    private void switchNightMode() {
        boolean isNight = PreferenceUtils.getBoolean(ConstantUtil.SWITCH_MODE_KEY);
        if (isNight) {
            // 日间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            PreferenceUtils.setBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        } else {
            // 夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            PreferenceUtils.setBoolean(ConstantUtil.SWITCH_MODE_KEY, true);
        }
        ToastUtils.shortToast("click!  -  " + isNight);
        recreate();
    }

}
