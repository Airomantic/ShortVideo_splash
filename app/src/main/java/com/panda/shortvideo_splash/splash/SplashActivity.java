package com.panda.shortvideo_splash.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.panda.shortvideo_splash.base.BaseActivity;
import com.panda.shortvideo_splash.main.MainActivity;
import com.panda.shortvideo_splash.R;
import com.panda.shortvideo_splash.base.ViewInject;

import java.io.File;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityContract.Iview {

    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoview;
    @BindView(R.id.tv_splash_timer)
    TextView mTvTimer;
    //全局变量快捷键ctrl+alt+F
    //private CustomCountDownTimer timer;
    /**
     * 此时进行P层时，需要进行抽象的编程
     */
    private ISplashActivityContract.IPresenter timerPresenter;

    private void initTimerPresenter() {
        //View层持有P层的引用，在View层创建P层强引用的对象，所以创建一个类
        timerPresenter = new SplashTimerPresenter(this);
        timerPresenter.initTimer();
    }

    private void initVideo() {
        mVideoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    private void initListener() {
        //为点击跳过设立点击事件，这是IOC数据回调，也是观察者设计模式
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //匿名内部类，this是请求不到当前活动的，活动是继承context的
                //startActivity(new Intent(this,MainActivity.class));
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                //注意startActivity()之后需要finish()，关闭掉当前页面，跳转到新的页面
                finish();
            }
        });
        mVideoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override                  //直接new一个匿名内部类
            public void onPrepared(MediaPlayer mp) {
                mp.start(); //IOC的数据回调
            }
        });
    }

    //界面销毁时，回调
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        timerPresenter.onDestroy(); //此时SplashTimerPresenter类就有onDestroy()了
//        //timerPresenter.cancel();
//        //timer.cancel();
//    }

    //此时这个是重写ISplashActivityContract接口内部接口下定义的方法
    @Override
    public void setTvTimer(String s) {
        mTvTimer.setText(s);
    }

    /**
     *  主要用于界面跳转，传递数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void afterBindView() {
        initTimerPresenter();
        /**闲置状态
         * 准备状态
         * 播放完成
         */
        initListener();
        initVideo();
    }
}
