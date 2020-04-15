package com.panda.shortvideo_splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    //全局变量快捷键ctrl+alt+F
    private FullScreenVideoView mVideoview;
    private TextView mTvTimer;
    private CustomCountDownTimer timer;

    //savedInstanceState 界面销毁的时候，保证这个界面的状态
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mVideoview =(FullScreenVideoView)findViewById(R.id.vv_play);
        mTvTimer =(TextView)findViewById(R.id.tv_splash_timer);
        //为点击跳过设立点击事件，这是IOC数据回调，也是观察者设计模式
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //匿名内部类，this是请求不到当前活动的，活动是继承context的
                //startActivity(new Intent(this,MainActivity.class));
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        });
        mVideoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ File.separator+R.raw.splash));
        //闲置状态
        //准备状态
        mVideoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override                  //直接new一个匿名内部类
            public void onPrepared(MediaPlayer mp) {
                mp.start(); //IOC的数据回调
            }
        });
        //播放完成
        mVideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
        //调用智能倒计时类 5s数据回调
        timer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                mTvTimer.setText(time + "秒");
            }

            @Override
            public void onFinish() {
                mTvTimer.setText("跳过");
            }
        });
        timer.start();
    }

    //界面销毁时，回调
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
