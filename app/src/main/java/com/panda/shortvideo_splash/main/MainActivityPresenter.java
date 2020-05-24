package com.panda.shortvideo_splash.main;

import android.support.v4.app.Fragment;

import com.panda.shortvideo_splash.R;
import com.panda.shortvideo_splash.main.beijing.BeiJingFragment;
import com.panda.shortvideo_splash.main.hangzhou.HangZhouFragment;
import com.panda.shortvideo_splash.main.shanghai.ShangHaiFragment;
import com.panda.shortvideo_splash.main.shenzhen.ShenZhenFragment;
import com.panda.shortvideo_splash.mvp.base.BaseMvpPresenter;

/**
 * @author jiangzq
 * @description:
 * @date :2020/5/23 9:15
 */
public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.Iview> implements IMainActivityContract.IPresenter {

    //当前Fragment角标
    private int mCurrentFragmentIndex;
    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentCheckedId;
    private int mTopPosition;
    private int mBottonPosition;

    public MainActivityPresenter(IMainActivityContract.Iview view) {
        super(view);
    }

    @Override
    protected IMainActivityContract.Iview getEmptyView() {
        return IMainActivityContract.emptyView;
    }

    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = 0;
        replaceFragment(mCurrentFragmentIndex);
    }

    @Override
    public int getCurrentCheckedId() {
        //注意这句
        return mCurrentCheckedId;
    }

    @Override
    public int getCurrentCheckedIndex() {
        return mCurrentFragmentIndex;
    }

    //切换Fragment方法
    public void replaceFragment(int mCurrentFragmentIndex) {
        for (int i = 0; i < mFragments.length; i++) {
            if (mCurrentFragmentIndex != i) {
                //避免空指针
                if (mFragments[i] != null) {
                    hideFragment(mFragments[i]);
                }
            }
        }
        //要让碎片显示出来，通过索引，先拿到它的实例
        Fragment mFragment = mFragments[mCurrentFragmentIndex];
        if (mFragment != null) {
            addAndShowFragment(mFragment);
            //记录下传进来的角标
            setCurChecked(mCurrentFragmentIndex);
        } else {
            newCurrentFragment(mCurrentFragmentIndex);
        } 
    }

    @Override
    public int getTopPosition() {
        return mTopPosition;
    }

    @Override
    public int getBottomPosition() {
        return mBottonPosition;
    }

    //记录当前角标
    private void setCurChecked(int mCurrentFragmentIndex) {

        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex){
            case 0:
                mCurrentCheckedId = R.id.rb_main_shanghai;
                mTopPosition = 0;
                break;
            case 1:
                mCurrentCheckedId = R.id.rb_main_hangzhou;
                mTopPosition = 1;
                break;
            case 2:
                mCurrentCheckedId = R.id.rb_main_beijing;
                mBottonPosition =2;
                break;
            case 3:
                mCurrentCheckedId = R.id.rb_main_shenzhen;
                mBottonPosition =3;
                break;
        }
    }

    //创建当前Fragment
    private void newCurrentFragment(int mCurrentFragmentIndex) {
        Fragment fragment = null;
        switch (mCurrentFragmentIndex) {
            case 0:
                fragment = new ShangHaiFragment();
                break;
            case 1:
                fragment = new HangZhouFragment();
                break;
            case 2:
                fragment = new BeiJingFragment();
                break;
            case 3:
                fragment = new ShenZhenFragment();
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment; //加进这个数组集合
        addAndShowFragment(fragment); //显示出来
    }

    //显示Fragment
    private void addAndShowFragment(Fragment mFragment) {
        if (mFragment.isAdded()) {
            getView().showFragment(mFragment);
        } else {
            getView().addFragment(mFragment);
        }
    }

    //隐藏Fragment
    private void hideFragment(Fragment mFragment) {
        if (mFragment != null&&mFragment.isVisible()) {
            getView().hideFragment(mFragment);
        }

    }
}
