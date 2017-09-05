package com.jjkopen.demo.module.comman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjkopen.demo.R;
import com.jjkopen.demo.utils.ToastUtils;
import com.jjkopen.demo.widget.banner.BannerEntity;
import com.jjkopen.demo.widget.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {
    @BindView(R.id.banner)
    BannerView banner;
    private List<BannerEntity> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        list.add(new BannerEntity("http://www.bilibili.com/video/av11935961/", "其实我点的是炸鸡饭啦~", "http://i0.hdslb.com/bfs/archive/2c0d1652fcde66b215808e1d25a974e91a399521.jpg"));
        list.add(new BannerEntity("http://www.bilibili.com/video/av11476258/", "“特级好菇道！”", "http://i0.hdslb.com/bfs/archive/1b630f0bf3f44648bbd8e70c7eadfb6476ef5567.jpg"));
        list.add(new BannerEntity("http://www.bilibili.com/video/av11845948/", "——这是作为瓦片的人生哲理", "http://i0.hdslb.com/bfs/archive/4a79e90ef27c704a88e98232bbccbd6eaf22e1b7.jpg"));
        list.add(new BannerEntity("http://www.bilibili.com/video/av11310223/", "一个螺帽的创意", "http://i0.hdslb.com/bfs/archive/c697500f20fc1559d638fb901b203a3a83416d1b.jpg"));
        banner.delayTime(3).build(list);


    }
}
