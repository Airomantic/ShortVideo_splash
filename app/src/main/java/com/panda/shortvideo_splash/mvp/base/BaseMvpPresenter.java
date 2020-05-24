package com.panda.shortvideo_splash.mvp.base;

import android.content.Intent;
import android.os.Bundle;

import com.panda.shortvideo_splash.mvp.IMvpView;
import com.panda.shortvideo_splash.mvp.presenter.LifeCircleMvpPresenter;

/**
 * @author jiangzq
 * @description: 桥接作用-P层的中间类
 *                泛型T继承也是个基类，只是为了复用一些方法，不用具体类去做了
 * @date :2020/5/19 7:39
 */
public abstract class BaseMvpPresenter<T extends IMvpView> extends LifeCircleMvpPresenter<T> {

    /**
     * getEmptyView()这个需要具体，比如SplashTimerPresenter类去实现的
     *
     * @return
     */
    /*@Override
    protected T getEmptyView() {
        return null;
    }*/
    //注意这一行
    public BaseMvpPresenter(T view) {
        super(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }
}
