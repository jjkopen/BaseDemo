package com.jjkopen.demo.eventbus;

/**
 * Created by jjkopen on 2017/7/23 0023.
 */

public class SplashEvent {
    private boolean isFinish;

    public SplashEvent(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
