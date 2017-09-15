package com.jjkopen.demo.widget;

/**
 * Created by jjkopen on 2017/7/23 0023.
 * 解决大量图片帧动画真机OOM,传入ImageView,图片[],duration(或duration[])
 * 替换原 帧动画
 */

import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.jjkopen.demo.R;
import com.jjkopen.demo.eventbus.SplashEvent;

import org.greenrobot.eventbus.EventBus;


public class ImageAnimation {

    private int[] frameRes = {
            R.drawable.white0000, R.drawable.white0001, R.drawable.white0002, R.drawable.white0003, R.drawable.white0004, R.drawable.white0005, R.drawable.white0006, R.drawable.white0007, R.drawable.white0008, R.drawable.white0009, R.drawable.white0010, R.drawable.white0011, R.drawable.white0012, R.drawable.white0013, R.drawable.white0014, R.drawable.white0015, R.drawable.white0016, R.drawable.white0017, R.drawable.white0018, R.drawable.white0019, R.drawable.white0020, R.drawable.white0021, R.drawable.white0022, R.drawable.white0023, R.drawable.white0024, R.drawable.white0025, R.drawable.white0026, R.drawable.white0027, R.drawable.white0028, R.drawable.white0029, R.drawable.white0030, R.drawable.white0031, R.drawable.white0032, R.drawable.white0033, R.drawable.white0034, R.drawable.white0035, R.drawable.white0036, R.drawable.white0037, R.drawable.white0038, R.drawable.white0039, R.drawable.white0040, R.drawable.white0041, R.drawable.white0042, R.drawable.white0043, R.drawable.white0044, R.drawable.white0045, R.drawable.white0046, R.drawable.white0047, R.drawable.white0048, R.drawable.white0049, R.drawable.white0050, R.drawable.white0051, R.drawable.white0052, R.drawable.white0053, R.drawable.white0054, R.drawable.white0055, R.drawable.white0056, R.drawable.white0057, R.drawable.white0058, R.drawable.white0059, R.drawable.white0060, R.drawable.white0061, R.drawable.white0062, R.drawable.white0063, R.drawable.white0064, R.drawable.white0065, R.drawable.white0066, R.drawable.white0067, R.drawable.white0068, R.drawable.white0069, R.drawable.white0070, R.drawable.white0071, R.drawable.white0072, R.drawable.white0073, R.drawable.white0074, R.drawable.white0075, R.drawable.white0076, R.drawable.white0077, R.drawable.white0078, R.drawable.white0079, R.drawable.white0080, R.drawable.white0081, R.drawable.white0082, R.drawable.white0083, R.drawable.white0084, R.drawable.white0085, R.drawable.white0086, R.drawable.white0087, R.drawable.white0088, R.drawable.white0089, R.drawable.white0090, R.drawable.white0091, R.drawable.white0092, R.drawable.white0093, R.drawable.white0094, R.drawable.white0095, R.drawable.white0096, R.drawable.white0097, R.drawable.white0098, R.drawable.white0099, R.drawable.white0100, R.drawable.white0101, R.drawable.white0102, R.drawable.white0103, R.drawable.white0104, R.drawable.white0105, R.drawable.white0106, R.drawable.white0107, R.drawable.white0108, R.drawable.white0109, R.drawable.white0110, R.drawable.white0111, R.drawable.white0112, R.drawable.white0113, R.drawable.white0114, R.drawable.white0115, R.drawable.white0116, R.drawable.white0117, R.drawable.white0118, R.drawable.white0119, R.drawable.white0120, R.drawable.white0121, R.drawable.white0122, R.drawable.white0123, R.drawable.white0124, R.drawable.white0125, R.drawable.white0126, R.drawable.white0127, R.drawable.white0128, R.drawable.white0129, R.drawable.white0130, R.drawable.white0131, R.drawable.white0132, R.drawable.white0133, R.drawable.white0134, R.drawable.white0135, R.drawable.white0136, R.drawable.white0137, R.drawable.white0138, R.drawable.white0139, R.drawable.white0140, R.drawable.white0141, R.drawable.white0142, R.drawable.white0143, R.drawable.white0144, R.drawable.white0145, R.drawable.white0146, R.drawable.white0147, R.drawable.white0148, R.drawable.white0149, R.drawable.white0150, R.drawable.white0151, R.drawable.white0152, R.drawable.white0153, R.drawable.white0154, R.drawable.white0155, R.drawable.white0156, R.drawable.white0157, R.drawable.white0158, R.drawable.white0159, R.drawable.white0160, R.drawable.white0161, R.drawable.white0162, R.drawable.white0163, R.drawable.white0164, R.drawable.white0165, R.drawable.white0166, R.drawable.white0167, R.drawable.white0168, R.drawable.white0169, R.drawable.white0170, R.drawable.white0171, R.drawable.white0172, R.drawable.white0173, R.drawable.white0174, R.drawable.white0175, R.drawable.white0176, R.drawable.white0177, R.drawable.white0178, R.drawable.white0179, R.drawable.white0180, R.drawable.white0181, R.drawable.white0182, R.drawable.white0183, R.drawable.white0184, R.drawable.white0185, R.drawable.white0186, R.drawable.white0187, R.drawable.white0188, R.drawable.white0189, R.drawable.white0190, R.drawable.white0191, R.drawable.white0192, R.drawable.white0193, R.drawable.white0194, R.drawable.white0195, R.drawable.white0196, R.drawable.white0197, R.drawable.white0198, R.drawable.white0199, R.drawable.white0200, R.drawable.white0201, R.drawable.white0202, R.drawable.white0203, R.drawable.white0204, R.drawable.white0205, R.drawable.white0206, R.drawable.white0207, R.drawable.white0208, R.drawable.white0209, R.drawable.white0210, R.drawable.white0211, R.drawable.white0212, R.drawable.white0213, R.drawable.white0214, R.drawable.white0215, R.drawable.white0216, R.drawable.white0217, R.drawable.white0218, R.drawable.white0219, R.drawable.white0220, R.drawable.white0221, R.drawable.white0222, R.drawable.white0223, R.drawable.white0224, R.drawable.white0225, R.drawable.white0226, R.drawable.white0227, R.drawable.white0228, R.drawable.white0229, R.drawable.white0230, R.drawable.white0231, R.drawable.white0232, R.drawable.white0233, R.drawable.white0234, R.drawable.white0235, R.drawable.white0236, R.drawable.white0237, R.drawable.white0238, R.drawable.white0239, R.drawable.white0240, R.drawable.white0241, R.drawable.white0242, R.drawable.white0243, R.drawable.white0244, R.drawable.white0245, R.drawable.white0246, R.drawable.white0247, R.drawable.white0248, R.drawable.white0249, R.drawable.white0250, R.drawable.white0251, R.drawable.white0252, R.drawable.white0253, R.drawable.white0254, R.drawable.white0255, R.drawable.white0256, R.drawable.white0257, R.drawable.white0258, R.drawable.white0259, R.drawable.white0260, R.drawable.white0261, R.drawable.white0262, R.drawable.white0263, R.drawable.white0264, R.drawable.white0265, R.drawable.white0266, R.drawable.white0267, R.drawable.white0268, R.drawable.white0269, R.drawable.white0270, R.drawable.white0271, R.drawable.white0272, R.drawable.white0273, R.drawable.white0274, R.drawable.white0275, R.drawable.white0276, R.drawable.white0277, R.drawable.white0278, R.drawable.white0279, R.drawable.white0280, R.drawable.white0281, R.drawable.white0282, R.drawable.white0283, R.drawable.white0284, R.drawable.white0285, R.drawable.white0286, R.drawable.white0287, R.drawable.white0288, R.drawable.white0289, R.drawable.white0290, R.drawable.white0291, R.drawable.white0292, R.drawable.white0293, R.drawable.white0294, R.drawable.white0295, R.drawable.white0296, R.drawable.white0297, R.drawable.white0298, R.drawable.white0299, R.drawable.white0300, R.drawable.white0301, R.drawable.white0302, R.drawable.white0303, R.drawable.white0304, R.drawable.white0305, R.drawable.white0306, R.drawable.white0307, R.drawable.white0308, R.drawable.white0309, R.drawable.white0310, R.drawable.white0311, R.drawable.white0312, R.drawable.white0313, R.drawable.white0314, R.drawable.white0315, R.drawable.white0316, R.drawable.white0317, R.drawable.white0318, R.drawable.white0319, R.drawable.white0320, R.drawable.white0321, R.drawable.white0322, R.drawable.white0323, R.drawable.white0324, R.drawable.white0325, R.drawable.white0326, R.drawable.white0327, R.drawable.white0328, R.drawable.white0329, R.drawable.white0330, R.drawable.white0331, R.drawable.white0332, R.drawable.white0333, R.drawable.white0334, R.drawable.white0335, R.drawable.white0336, R.drawable.white0337, R.drawable.white0338, R.drawable.white0339, R.drawable.white0340, R.drawable.white0341, R.drawable.white0342, R.drawable.white0343, R.drawable.white0344, R.drawable.white0345, R.drawable.white0346, R.drawable.white0347, R.drawable.white0348, R.drawable.white0349, R.drawable.white0350, R.drawable.white0351, R.drawable.white0352, R.drawable.white0353, R.drawable.white0354, R.drawable.white0355, R.drawable.white0356, R.drawable.white0357, R.drawable.white0358, R.drawable.white0359, R.drawable.white0360, R.drawable.white0361, R.drawable.white0362, R.drawable.white0363, R.drawable.white0364, R.drawable.white0365, R.drawable.white0366, R.drawable.white0367, R.drawable.white0368, R.drawable.white0369, R.drawable.white0370, R.drawable.white0371, R.drawable.white0372, R.drawable.white0373, R.drawable.white0374, R.drawable.white0375, R.drawable.white0376, R.drawable.white0377, R.drawable.white0378, R.drawable.white0379, R.drawable.white0380, R.drawable.white0381, R.drawable.white0382, R.drawable.white0383, R.drawable.white0384, R.drawable.white0385, R.drawable.white0386, R.drawable.white0387, R.drawable.white0388, R.drawable.white0389, R.drawable.white0390, R.drawable.white0391, R.drawable.white0392, R.drawable.white0393, R.drawable.white0394, R.drawable.white0395, R.drawable.white0396, R.drawable.white0397, R.drawable.white0398, R.drawable.white0399, R.drawable.white0400, R.drawable.white0401, R.drawable.white0402, R.drawable.white0403, R.drawable.white0404, R.drawable.white0405, R.drawable.white0406, R.drawable.white0407, R.drawable.white0408, R.drawable.white0409, R.drawable.white0410, R.drawable.white0411, R.drawable.white0412, R.drawable.white0413, R.drawable.white0414, R.drawable.white0415, R.drawable.white0416, R.drawable.white0417, R.drawable.white0418, R.drawable.white0419, R.drawable.white0420, R.drawable.white0421, R.drawable.white0422, R.drawable.white0423, R.drawable.white0424, R.drawable.white0425, R.drawable.white0426, R.drawable.white0427, R.drawable.white0428, R.drawable.white0429, R.drawable.white0430, R.drawable.white0431, R.drawable.white0432, R.drawable.white0433, R.drawable.white0434, R.drawable.white0435, R.drawable.white0436, R.drawable.white0437, R.drawable.white0438, R.drawable.white0439, R.drawable.white0440, R.drawable.white0441, R.drawable.white0442, R.drawable.white0443, R.drawable.white0444, R.drawable.white0445, R.drawable.white0446
    };

    private Handler handler;//线程处理
    private MovieAction action;//播放器


    //固定循环时间
    public ImageAnimation(ImageView view, int duration) {
        int len = frameRes.length;
        int[] frameDuration = new int[len];
        for (int i = 0; i < len; i++) {
            frameDuration[i] = duration;
        }
        this.Init(view, frameRes, frameDuration);
    }

    //固定循环时间
    public ImageAnimation(ImageView view, int[] frameRes, int duration) {
        int len = frameRes.length;
        int[] frameDuration = new int[len];
        for (int i = 0; i < len; i++) {
            frameDuration[i] = duration;
        }
        this.Init(view, frameRes, frameDuration);
    }

    //非固定循环时间
    public ImageAnimation(ImageView view, int[] frameRes, int[] frameDuration) {
        this.Init(view, frameRes, frameDuration);

    }

    private void Init(ImageView view, int[] frameRes, int[] frameDuration) {

        if (null == view) {
            Log.e("ImageAnimation", "创建动画失败。");
        } else if (null == frameRes || null == frameDuration || 0 == frameRes.length || 0 == frameDuration.length) {
            Log.e("ImageAnimation", "帧数或资源为空.");
        } else if (frameRes.length != frameDuration.length) {
            Log.e("ImageAnimation", "帧数与间隔时间不匹配");
        } else {
            handler = new Handler();
            action = new MovieAction(handler, view, frameRes, frameDuration);
        }
    }

    public void cancel() {
        action.destory();
    }


}


//动画播放类
class MovieAction implements Runnable {

    private ImageView mView;//画布
    private int[] mFrameRes;//资源
    private int[] mFrameDuration;//间隔
    private int currentFrame;//当前帧
    private int totalFrame;//总帧数

    private Handler mHandler;//线程

    public MovieAction(Handler handler, ImageView view, int[] frameRes, int[] frameDuration) {
        this.mView = view;
        this.mFrameRes = frameRes;
        this.mFrameDuration = frameDuration;
        this.mHandler = handler;

        totalFrame = this.mFrameRes.length;
        currentFrame = 0;
        mHandler.postDelayed(this, mFrameDuration[currentFrame]);
    }

    public void destory() {
        this.mHandler.removeCallbacks(this);
    }


    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {
        mView.setImageResource(mFrameRes[currentFrame]);

        currentFrame++;
        if (++currentFrame < totalFrame) {
            mHandler.postDelayed(this, mFrameDuration[currentFrame]);
        } else {
            System.out.println("destory------");
            destory();
            EventBus.getDefault().post(new SplashEvent(true));
        }
    }
}