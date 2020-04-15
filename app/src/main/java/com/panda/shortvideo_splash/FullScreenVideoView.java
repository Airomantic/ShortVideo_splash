package com.panda.shortvideo_splash;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @author jiangzq
 * @description:
 * @date :2020/4/12 10:37
 */
public class FullScreenVideoView extends VideoView {

    private int width;
    private int height;

    //主要用于直接new出来的对象
    public FullScreenVideoView(Context context) {
        super(context);
    }

    //主要用于xml文件中，支持自定义属性
    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //也主要用于xml文件中，支持自定义属性，同时支持style样式
    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //重写

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //widthMeasureSpec 1.测量模式 2.测量大小
        width = getDefaultSize(0, widthMeasureSpec);
        height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width,height);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec); //这个方法有问题
    }
}
