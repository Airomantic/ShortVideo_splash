package com.panda.shortvideo_splash.main;

import android.support.v4.app.Fragment;

import com.panda.shortvideo_splash.mvp.ILifeCircle;
import com.panda.shortvideo_splash.mvp.IMvpView;
import com.panda.shortvideo_splash.mvp.MvpController;

/**
 * @author jiangzq
 * @description: 面向抽象编程（接口比抽象类还抽象）
 * @date :2020/5/21 17:54
 */
public interface IMainActivityContract {

    interface Iview extends IMvpView {
        void showFragment(Fragment mFragment);

        void addFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);
        //定义方法
        
    }

    interface IPresenter extends ILifeCircle {

        void initHomeFragment();

        int getCurrentCheckedId();

        int getCurrentCheckedIndex();

        void replaceFragment(int mCurrentFragmentIndex);

        int getTopPosition();

        int getBottomPosition();
    }
    //创建一个Iview对象，这里让SplashTimerPresenter调用这个对象
    Iview emptyView =new Iview() {


        @Override
        public void showFragment(Fragment mFragment) {

        }

        @Override
        public void addFragment(Fragment mFragment) {

        }

        @Override
        public void hideFragment(Fragment mFragment) {

        }

        @Override
        public MvpController getMvpController() {
            return null;
        }
    };
}
