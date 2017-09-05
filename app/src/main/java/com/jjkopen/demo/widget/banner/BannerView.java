package com.jjkopen.demo.widget.banner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jjkopen.demo.R;
import com.jjkopen.demo.utils.SysUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 无限Banner
 */
public class BannerView extends RelativeLayout implements BannerAdapter.ViewPagerOnItemClickListener {
    @BindView(R.id.layout_banner_viewpager)
    ViewPager viewPager;
    @BindView(R.id.layout_banner_points_group)
    LinearLayout points;
    //默认轮播时间，10s
    private int delayTime = 10;
    private List<ImageView> imageViewList;
    private List<BannerEntity> bannerList;
    //选中显示Indicator
    private int selectRes = R.drawable.shape_dots_select;
    //非选中显示Indicator
    private int unSelcetRes = R.drawable.shape_dots_default;
    //当前页的下标
    private int currentPos;

    private Handler handler = new Handler();

    public BannerView(Context context) {
        this(context, null);
    }


    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_banner, this, true);
        ButterKnife.bind(this);
        imageViewList = new ArrayList<>();
    }


    /**
     * 设置轮播间隔时间
     *
     * @param time 轮播间隔时间，单位秒
     */
    public BannerView delayTime(int time) {
        this.delayTime = time;
        return this;
    }


    /**
     * 设置Points资源 Res
     *
     * @param selectRes   选中状态
     * @param unselcetRes 非选中状态
     */
    public void setPointsRes(int selectRes, int unselcetRes) {
        this.selectRes = selectRes;
        this.unSelcetRes = unselcetRes;
    }


    /**
     * 图片轮播需要传入参数
     */
    public void build(List<BannerEntity> list) {
        destroy();
        if (list.size() == 0) {
            this.setVisibility(GONE);
            return;
        }
        bannerList = new ArrayList<>();
        bannerList.addAll(list);
        final int pointSize;
        pointSize = bannerList.size();
        if (pointSize == 2) {
            bannerList.addAll(list);
        }
        //判断是否清空 指示器点
        if (points.getChildCount() != 0) {
            points.removeAllViewsInLayout();
        }
        //初始化与个数相同的指示器点
        for (int i = 0; i < pointSize; i++) {
            View dot = new View(getContext());
            dot.setBackgroundResource(unSelcetRes);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    SysUtils.dip2px(getContext(), 5),
                    SysUtils.dip2px(getContext(), 5));
            params.leftMargin = 10;
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            points.addView(dot);
        }
        points.getChildAt(0).setBackgroundResource(selectRes);
        for (int i = 0; i < bannerList.size(); i++) {
            ImageView mImageView = new ImageView(getContext());
            Glide.with(getContext())
                    .load(bannerList.get(i).img)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(mImageView);
            imageViewList.add(mImageView);
        }
        //监听图片轮播，改变指示器状态
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                pos = pos % pointSize;
                currentPos = pos;
                for (int i = 0; i < points.getChildCount(); i++) {
                    points.getChildAt(i).setBackgroundResource(unSelcetRes);
                }
                points.getChildAt(pos).setBackgroundResource(selectRes);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (isStopScroll) {
                            startScroll();
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        stopScroll();
                        break;
                }
            }
        });
        BannerAdapter bannerAdapter = new BannerAdapter(imageViewList);
        viewPager.setAdapter(bannerAdapter);
        bannerAdapter.notifyDataSetChanged();
        bannerAdapter.setmViewPagerOnItemClickListener(this);
        //图片开始轮播
        startScroll();
    }

    private void destroy() {
        handler.removeCallbacks(r);
    }

    private boolean isStopScroll = false;
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            if (isStopScroll) {
                return;
            }
            isStopScroll = true;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };

    /**
     * 图片开始轮播
     */
    private void startScroll() {
        isStopScroll = false;
        handler.postDelayed(r, delayTime * 1000);
    }


    /**
     * 图片停止轮播
     */
    private void stopScroll() {
        isStopScroll = true;
        handler.removeCallbacks(r);
    }


    /**
     * 设置ViewPager的Item点击回调事件
     */
    @Override
    public void onItemClick() {
//    BrowserActivity.launch((Activity) getContext(),
//        bannerList.get(currentPos).link,
//        bannerList.get(currentPos).title);
    }
}
