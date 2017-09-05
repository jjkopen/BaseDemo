package com.jjkopen.demo.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.jjkopen.demo.base.MyApp;

/**
 * Created by jjkopen on 2017/7/23 0023.
 */

public class ToastUtils {
    private static Toast toast;

    public static void shortToast(String info) {
        toast = Toast.makeText(MyApp.getInstance(), info, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void shortToastThread(String info) {
        new Handler(Looper.getMainLooper()).post(() -> shortToast(info));
    }

    public static void longToast(String info) {
        toast = Toast.makeText(MyApp.getInstance(), info, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void longToastThread(String info) {
        new Handler(Looper.getMainLooper()).post(() -> longToast(info));
    }

    public static void showToastContinue(String info) {
        if (toast == null) {
            toast = Toast.makeText(MyApp.getInstance(), info, Toast.LENGTH_LONG);
            toast.show();
        } else {
            toast.setText(info);
            toast.show();
        }
    }

    public static void showToastContinueThread(String info) {
        new Handler(Looper.getMainLooper()).post(() -> showToastContinue(info));
    }

}
