package com.panda.shortvideo_splash.mvp;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jiangzq
 * @description: 它要对应到T层的生命周期
 * 一整套静态代理的生命周期的关联
 * @date :2020/5/19 9:26
 */
public class MvpController implements ILifeCircle{

    //存放的是 P层的实例
    private Set<ILifeCircle> lifeCircles = new HashSet<>();

    public void savePresenter(ILifeCircle lifeCircle) {
        this.lifeCircles.add(lifeCircle); //把当前的P层实例对象传入进去
    }
    /**
     *
     * @param savedInstanceState 由Activity底层返回
     * @param intent 跳转
     * @param getArguments 参数传递
     */
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
    public void onDestroy() {

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

    @Override
    public void attachView(IMvpView iMvpView) {

    }
}
