package com.jjkopen.demo.module.comman;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjkopen.demo.R;
import com.jjkopen.demo.base.BaseActivity;
import com.jjkopen.demo.greendao.bean.UserName;
import com.jjkopen.demo.greendao.dao.LoginDao;
import com.jjkopen.demo.utils.ConstantUtil;
import com.jjkopen.demo.utils.ImgCode;
import com.jjkopen.demo.utils.PreferenceUtils;
import com.jjkopen.demo.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.userName)
    TextInputLayout mUserName;
    @BindView(R.id.edt_username)
    AutoCompleteTextView edtUserName;
    @BindView(R.id.password)
    TextInputLayout mPassword;
    @BindView(R.id.imgcode)
    TextInputLayout mImgcode;
    @BindView(R.id.ll_imgcode)
    LinearLayout mLlImgcode;
    @BindView(R.id.iv_imgcode)
    ImageView mIvImgcode;
    @BindView(R.id.cb_username)
    CheckBox cbUsername;
    @BindView(R.id.btn_clearusername)
    TextView btnClearusername;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    private int errorCount = 0;

    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        List<UserName> userNameList = new ArrayList<>();
        userNameList = LoginDao.queryAll();
        List<String> list = new ArrayList<>();
        for (UserName name : userNameList) {
            list.add(name.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        edtUserName.setAdapter(adapter);
        btnClearusername.setOnClickListener(v -> showClearDialog());
    }

    @OnClick({R.id.btn_login, R.id.iv_imgcode})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                errorCount++;
                setErrorNull();
                String username = mUserName.getEditText().getText().toString();
                String password = mPassword.getEditText().getText().toString();
                String code = mImgcode.getEditText().getText().toString();
                if (errorCount > 2 && mLlImgcode.getVisibility() == View.GONE) {
                    mLlImgcode.setVisibility(View.VISIBLE);
                    mIvImgcode.setImageBitmap(ImgCode.getInstance().createBitmap());
                    return;
                }

                if (!TextUtils.validateEmail(username)) {
                    mUserName.setError("无效的邮箱!");
                } else if (!TextUtils.validatePassword(password)) {
                    mPassword.setError("密码必须大于五位!");
                    mUserName.setErrorEnabled(false);
                } else if (mLlImgcode.getVisibility() == View.VISIBLE) {
                    if (android.text.TextUtils.isEmpty(code)) {
                        mImgcode.setError("验证码不能为空!");
                    } else if (!code.equalsIgnoreCase(ImgCode.getInstance().getCode())) {
                        mImgcode.setError("验证码错误!");
                    } else {
                        hideKeyboard();
                        doLogin();
                    }
                } else {
                    hideKeyboard();
                    doLogin();
                }
                break;
            case R.id.iv_imgcode:
                mIvImgcode.setImageBitmap(ImgCode.getInstance().createBitmap());
                break;
        }
    }

    private void setErrorNull() {
        mUserName.setError(null);
        mPassword.setError(null);
        mImgcode.setError(null);
        mUserName.setErrorEnabled(false);
        mPassword.setErrorEnabled(false);
        mImgcode.setErrorEnabled(false);
    }

    private void doLogin() {
        mBtnLogin.setEnabled(false);
        PreferenceUtils.setString(ConstantUtil.USERNAME, mUserName.getEditText().getText().toString());
        PreferenceUtils.setString(ConstantUtil.PASSWORD, mPassword.getEditText().getText().toString());
        if (cbUsername.isChecked()) {
            PreferenceUtils.setBoolean(ConstantUtil.AUTOLOGIN, true);
        } else {
            PreferenceUtils.setBoolean(ConstantUtil.AUTOLOGIN, false);
        }
        UserName name = new UserName();
        name.setName(mUserName.getEditText().getText().toString());
        insertUserName(name);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }, 2000);
    }

    @Override
    protected void initData() {
        hideKeyboard();
        if (PreferenceUtils.getBoolean(ConstantUtil.AUTOLOGIN)) {
            mUserName.getEditText().setText(PreferenceUtils.getString(ConstantUtil.USERNAME));
            mPassword.getEditText().setText(PreferenceUtils.getString(ConstantUtil.PASSWORD));
            mBtnLogin.performClick();
        }
    }

    @Override
    protected void initActionBar() {

    }

    private void insertUserName(UserName name) {
        LoginDao.insertHistory(name);
    }

    private void clearUserName() {
        LoginDao.clearAll();
    }

    private void showClearDialog() {
        new AlertDialog.Builder(this)
                .setMessage("确认删除账号历史列表?")
                .setPositiveButton("确认", (dialog, which) -> clearUserName())
                .setNegativeButton("取消", null)
                .create().show();
    }

}
