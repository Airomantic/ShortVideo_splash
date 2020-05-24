package com.panda.shortvideo_splash.splash;

import com.panda.shortvideo_splash.mvp.ILifeCircle;
import com.panda.shortvideo_splash.mvp.IMvpView;
import com.panda.shortvideo_splash.mvp.MvpController;

/**
 * @author jiangzq
 * @description: 面向抽象编程（接口比抽象类还抽象）
 * @date :2020/5/21 17:54
 */
public interface ISplashActivityContract {

    interface Iview extends IMvpView {
        //定义一个字符方法
        void setTvTimer(String timer);
    }

    interface IPresenter extends ILifeCircle {
        void initTimer();
    }
    //创建一个Iview对象，这里让SplashTimerPresenter调用这个对象
    Iview emptyView =new Iview() {
        @Override
        public void setTvTimer(String timer) {

        }

        @Override
        public MvpController getMvpController() {
            return null;
        }
    };
}
