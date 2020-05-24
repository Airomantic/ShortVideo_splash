package com.panda.shortvideo_splash.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.panda.shortvideo_splash.mvp.IMvpView;
import com.panda.shortvideo_splash.mvp.MvpController;

/**
 * @author jiangzq
 * @description:  代理类-目标对象的相应逻辑
 * 生命周期的View层
 * （MVP底层框架View层的基类）
 * @date :2020/5/19 8:16
 */
public class LifeCircleMvpFragment extends Fragment implements IMvpView {

    //相当于Activity对应一个Controller，这个Controller会持有P层
    private MvpController mvpController;

    //一个View层对应一个静态代理的Controller
    @Override
    public MvpController getMvpController() {
        if (this.mvpController == null) {
            this.mvpController = new MvpController();
        }
        return this.mvpController;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) { //不为空，说明有P层，可以去调
            mvpController.onCreate(savedInstanceState,null,bundle);
            mvpController.onActivityCreated(savedInstanceState,null,bundle);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) { //不为空，说明有P层，可以去调
            mvpController.onActivityCreated(savedInstanceState,null,bundle);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) { //不为空，说明有P层，可以去调
            mvpController.onViewDestroy(); //注意!这里是onViewDestroy()
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) { //不为空，说明有P层，可以去调
            mvpController.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) { //不为空，说明有P层，可以去调
            mvpController.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) { //不为空，说明有P层，可以去调
            mvpController.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) { //不为空，说明有P层，可以去调
            mvpController.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) { //不为空，说明有P层，可以去调
            mvpController.onDestroy();
        }
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }*/
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) { //不为空，说明有P层，可以去调
            mvpController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) { //不为空，说明有P层，可以去调
            mvpController.onActivityResult(requestCode, resultCode, data);
        }
    }
}
