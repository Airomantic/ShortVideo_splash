package com.panda.shortvideo_splash.mvp;

import android.content.Intent;
import android.os.Bundle;

/**
 * @author jiangzq
 * @description: 抽象同事，维护P层生命周期
 * @date :2020/5/18 19:28
 */
public interface ILifeCircle {
    void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments);

    void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void destroyView();

    void onViewDestroy();

    void onNewIntent(Intent intent);

    /**
     * 主要用于界面跳转，传递数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    void onActivityResult(int requestCode, int resultCode,Intent data);

    void onSaveInstanceState(Bundle bundle);

    void attachView(IMvpView iMvpView);
}
