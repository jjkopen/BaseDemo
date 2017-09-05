package com.jjkopen.demo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.jjkopen.demo.R;

/**
 * 密码输入框
 * <p>
 * 关于输入完后触发,自己加个接口或者enentbus发送
 */

@SuppressLint("AppCompatCustomView")
public class PayEditText extends EditText {
    private Context context;
    private int mCount, index; //总数 当前输入数
    private int mWidth, mHeight;
    private String mText; //输入内容
    private Paint defaultPaint, whitePaint; //默认画笔 白色圆点画笔

    public PayEditText(Context context) {
        super(context);
    }

    public PayEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PayEditText, 0, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.PayEditText_count:
                    mCount = array.getInt(attr, 6);
                    break;
            }
        }
        array.recycle();
        setBackgroundResource(R.drawable.shape_payedittext_background);
        setImeOptions(EditorInfo.IME_ACTION_DONE);
        setCursorVisible(false);

        defaultPaint = new Paint();
        defaultPaint.setAntiAlias(true);//设置抗锯齿

        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);//设置抗锯齿
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setColor(Color.WHITE);

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setData(charSequence.toString().length(), charSequence.toString());
                if (charSequence.toString().length() >= mCount) {
                    listener.onFinish(true);
                } else {
                    listener.onFinish(false);
                }
                Log.e("---------", charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = measureDimension(widthMeasureSpec, "w");
        mHeight = measureDimension(heightMeasureSpec, "h");

        Log.e("--------", "mWidth---" + mWidth + ":mHeight---" + mHeight);
    }

    private int measureDimension(int measureSpec, String tag) {
        int result, defaultValue;
        if (tag.equals("w")) {
            defaultValue = dp2px(context, 50f * mCount);
        } else {
            defaultValue = dp2px(context, 50f);
        }
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {  //match or 具体值
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {  //wrap
            result = Math.min(defaultValue, specSize);
        } else {
            result = defaultValue;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mCount; i++) {
            if (i > 0 && i < mCount) {
                float w = mWidth * i * 1f / mCount;
                canvas.drawLine(w, 0, w, mHeight, defaultPaint);
            }
        }

        float w = mWidth * 1f / mCount;
        if (index <= 0) {
            canvas.drawCircle(w / 2, mHeight / 2, 10, whitePaint);
        } else if (index <= mCount) {
            for (int i = 1; i <= index; i++) {
                canvas.drawCircle(w / 2 + w * (i - 1), mHeight / 2, 10, defaultPaint);
            }
        } else {
        }
    }

    public void setData(int index, String data) {
        this.index = index;
        this.mText = data;
        invalidate();
    }

    public void setCount(int count) {
        this.mCount = count;
        invalidate();
    }

    public String getData() {
        return mText;
    }

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private OnFinishListener listener;

    public interface OnFinishListener {
        void onFinish(boolean isfinish);
    }

    public void setOnFinishListener(OnFinishListener listener) {
        this.listener = listener;
    }
}
