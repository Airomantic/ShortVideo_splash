package com.panda.shortvideo_splash.splash;

import android.util.Log;

import com.panda.shortvideo_splash.mvp.base.BaseMvpPresenter;

/**
 * @author jiangzq
 * @description: P层：View层的引用
 * @date :2020/5/18 11:56
 */
public class SplashTimerPresenter extends BaseMvpPresenter<ISplashActivityContract.Iview> implements ISplashActivityContract.IPresenter {

    //private final SplashActivity mActivity;
    private CustomCountDownTimer timer;


    /**
     * getEmptyView()这个需要具体，比如SplashTimerPresenter类去实现的
     */


    /**
     * 需要找到mTvTimer控件，更新相应的数据
     * 可以通过构造方法，把当前对象传递SplashTimerPresenter()
     */
    public SplashTimerPresenter(ISplashActivityContract.Iview view) {
        super(view);
    }


    public void initTimer() {
        //调用智能倒计时类 5s数据回调
        timer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                /**
                 * getView()在LifeCircleMvpPresenter的弱引用声明泛型去View层拿到实例
                 * 在
                 */
                getView().setTvTimer(time + "秒");
                //mTvTimer.setText(time + "秒");
            }

            @Override
            public void onFinish() {
                getView().setTvTimer("跳过");
                //mTvTimer.setText("跳过");
            }
        });
        timer.start();
    }

    public void cancel() {
        timer.cancel();
    }

    /*@Override
    protected IMvpView getEmptyView() {
        return null;
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
            cancel();
            Log.e("SplashTimerPresenter", "onDestroy");
            System.out.print("SplashActivity已到达onDestroy");
    }

    /**
     * 防止空指针即防止内存泄露
     * @return
     */
    @Override
    protected ISplashActivityContract.Iview getEmptyView() {
        return ISplashActivityContract.emptyView;
    }
}
