package com.jjkopen.demo.module.comman;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jjkopen.demo.R;
import com.jjkopen.demo.base.BaseActivity;
import com.jjkopen.demo.utils.ToastUtils;
import com.jjkopen.demo.widget.PayEditText;
import com.jjkopen.demo.widget.TextSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends Fragment {
    @BindView(R.id.seekbar)
    TextSeekBar seekBar;
    @BindView(R.id.pay)
    PayEditText pay;
    @BindView(R.id.btn)
    Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        seekBar.setValues(300l, 200000l, 15);
        pay.setOnFinishListener(isfinish -> btn.setEnabled(isfinish));
        btn.setOnClickListener(view -> ToastUtils.shortToast("买买买"));
    }
}
