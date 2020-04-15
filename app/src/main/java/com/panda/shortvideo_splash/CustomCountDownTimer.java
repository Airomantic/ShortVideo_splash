package com.panda.shortvideo_splash;


import android.os.Handler;

/**
 * @author jiangzq
 * @description: 智能减时
 * @date :2020/4/13 11:02
 */
public class CustomCountDownTimer implements Runnable {
    private int time;
    private int countDownTime;
    private final ICountDownHandler countDownHandler;
    private final Handler handler;
    private boolean isRun;

    //1、实时去回调 这个时刻是什么时刻 倒计时到几秒 观察者设计模式
    //2、支持动态传入总时间
    //3、每过一秒 总秒数减一
    //4、总时间倒计时为0时，要回调完成的状态

    //构造器
    public CustomCountDownTimer(int time, ICountDownHandler countDownHandler) {
        //转换成成员变量
        //注意这里是android.os包
        handler = new Handler();
        //this.time来自全局
        this.time = time;
        //countDownTime一开始是0，需要在这里设置传入time
        this.countDownTime = time;
        this.countDownHandler = countDownHandler;

    }

    //实现具体逻辑
    @Override
    public void run() {
        if (isRun) {
            if (countDownHandler != null) {
                //回调
                countDownHandler.onTicker(countDownTime);
            }
            if (countDownTime == 0) { //等于0说明已经减完了
                cancel();
                if (countDownHandler != null) {
                    countDownHandler.onFinish();
                }
            } else {
                countDownTime = time--;
                //延迟发送，延迟时间（每过一秒发送一些消息）
                handler.postDelayed(this, 1000);//1000ms=1s，延迟一秒发送
            }
        }
    }

    //设置handler启动开关，开启倒计时
    public void start() {
        isRun = true;
        handler.post(this); //这个this是Runnable，它会调用Run()方法
    }

    //避免内存泄露 ，跳出循环 终止
    public void cancel() {
        isRun = false;
        handler.removeCallbacks(this);
    }

    //观察者回调接口 （IOC 数据回调）
    public interface ICountDownHandler {
        //倒计时回调
        void onTicker(int time);
        //完成时回调
        void onFinish();
    }
}
