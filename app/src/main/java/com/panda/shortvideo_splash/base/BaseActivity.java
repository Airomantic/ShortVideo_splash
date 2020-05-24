package com.panda.shortvideo_splash.base;

import android.os.Bundle;

import com.panda.shortvideo_splash.mvp.view.LifeCircleMvpActivity;

import butterknife.ButterKnife;

/**
 * @author jiangzq
 * @description:
 * @date :2020/5/17 11:59
 */
public abstract class BaseActivity extends LifeCircleMvpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ctrl+alt+F全局变量
        //:Ctrl+Alt+V局部变量
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int mainLayoutId = annotation.mainLayoutId();
            if (mainLayoutId > 0) {
                setContentView(mainLayoutId);
                //注意要放在这个位置
                bindView();
                afterBindView();
            } else {
                throw new RuntimeException("mainLayoutId<0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
    }
    /**
     * 抽象类是没有private的，他需要靠继承来实现，因此需要公开
     * 模板方法 设计模式
     * 就是父类定义一条规则，具体的内容由子类去实现
     */
    public abstract void afterBindView() ;
    //view的依赖注入绑定
    private void bindView() {
        ButterKnife.bind(this);
    }
}
