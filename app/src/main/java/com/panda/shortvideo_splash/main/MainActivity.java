package com.panda.shortvideo_splash.main;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.panda.shortvideo_splash.R;
import com.panda.shortvideo_splash.base.BaseActivity;
import com.panda.shortvideo_splash.base.ViewInject;
import com.panda.shortvideo_splash.main.tools.MainConstantTool;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainLayoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.Iview{

    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);

    @BindView(R.id.fac_main_home)
    FloatingActionButton facMainHome;
    @BindView(R.id.rb_main_shanghai)
    RadioButton rbMainNavigationShanghai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton rbMainNavigationHangzhou;
    @BindView(R.id.fl_main_bottom)
    FrameLayout flMainBottom;
    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;
    @BindView(R.id.rb_main_beijing)
    RadioButton rbMainNavigationBeijing;
    @BindView(R.id.rb_main_shenzhen)
    RadioButton rbMainNavigationShenzhen;
    @BindView(R.id.rg_main_top)
    RadioGroup rgMainTop;
    private boolean isChangeTopOrBottom;

    @Override
    public void afterBindView() {
        initHomeFragment();
        changeAnimaton(rgMainBottom,rgMainTop);
    }

    //初始化Fragment
    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }

    @OnClick(R.id.fac_main_home)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom) {
                    changeAnimaton(rgMainTop,rgMainBottom);
                    handleTopPosition();
                } else {
                    changeAnimaton(rgMainBottom,rgMainTop);
                    handleBottomPosition();
                }
        }
    }

    //北京 深圳 是2,3
    private void handleBottomPosition() {
        //如果大于1，默认切换到0
        if (mPresenter.getBottomPosition() != MainConstantTool.HANGZHOU) {
            //默认进来就是上海
            mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
            rbMainNavigationBeijing.setChecked(true);
        } else {//如果=1了，就是杭州
            mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
            rbMainNavigationHangzhou.setChecked(true);
        }
    }

    //上海 杭州 是0,1
    private void handleTopPosition() {
        if (mPresenter.getBottomPosition() != MainConstantTool.SHENZHEN) {
            //切换了，就展示到北京
            mPresenter.replaceFragment(MainConstantTool.BEIJING);
            rbMainNavigationBeijing.setChecked(true);
        } else {//如果是大于2的，就把它切换回来
            mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
            rbMainNavigationShenzhen.setChecked(true);
        }
    }

    private void changeAnimaton(RadioGroup gone, RadioGroup show) {
        /**
         *  消失动画
         */
        gone.clearAnimation(); //清除自身动画
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);//系统自带AnimationUtils
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE); //把这个控件隐藏掉
        /**
         *  展示的动画，从底部划上来
         */
        show.clearAnimation(); //清除自身动画
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);//系统自带AnimationUtils
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE); //把这个控件显示出来
    }

    @Override
    public void showFragment(Fragment mFragment) {
        //注意不能少了commit()
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }

    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content, mFragment).commit();
    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }
}
