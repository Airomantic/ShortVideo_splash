package com.panda.shortvideo_splash.mvp.presenter;

import com.panda.shortvideo_splash.mvp.ILifeCircle;
import com.panda.shortvideo_splash.mvp.IMvpView;
import com.panda.shortvideo_splash.mvp.MvpController;

import java.lang.ref.WeakReference;

/**
 * @author jiangzq
 * @description: 抽象中介者，保存和获取View层的引用
 * @date :2020/5/18 19:55
 */
//泛型约束
public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {

    //弱引用成员变量
    protected WeakReference<T> weakReference;
    protected LifeCircleMvpPresenter() {
        super();
    }

    public LifeCircleMvpPresenter(IMvpView iMvpView) {
        super();
        attachView(iMvpView);
        MvpController mvpController = iMvpView.getMvpController();//把当前View层的Controller拿到
        mvpController.savePresenter(this);
    }

    /**
     *  attachView()做静态代理时，也就是View层和P层的生命周期相关联的时候，这个方法会自动调的
     * @param iMvpView
     */
    @Override
    public void attachView(IMvpView iMvpView) {
        //如果弱引用是存在的，就把它获取出来
        if (weakReference == null) {
            weakReference = new WeakReference(iMvpView);
        } else {
            //泛型体
            T view = (T) weakReference.get();
            //如果传递的对象不一致，则重新传一次，为确定iMvpView在P层实现数据同步
            if (view != iMvpView) {
                weakReference = new WeakReference(iMvpView);
            }
        }
    }
    //把弱引用切通掉
    @Override
    public void onDestroy() {

        weakReference = null;
    }

    protected T getView() {
        T view = weakReference != null ? (T) weakReference.get() : null;//需要强转泛型T
        if (view == null) {
            return getEmptyView();
        }
        return view; //不等于null时
    }

    protected abstract T getEmptyView();

}
