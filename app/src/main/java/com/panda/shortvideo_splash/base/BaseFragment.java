package com.panda.shortvideo_splash.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.shortvideo_splash.mvp.view.LifeCircleMvpActivity;
import com.panda.shortvideo_splash.mvp.view.LifeCircleMvpFragment;

import butterknife.ButterKnife;

/**
 * @author jiangzq
 * @description:
 * @date :2020/5/17 11:59
 */
public abstract class BaseFragment extends LifeCircleMvpFragment {

    private Context mContext;

    /**
     *  作用：就是为了传入context的
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = null;

        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int mainLayoutId = annotation.mainLayoutId();
            if (mainLayoutId > 0) {
                mView=initFragmentView(inflater,mainLayoutId);
                //注意要放在这个位置
                bindView(mView);
                afterBindView();
            } else {
                throw new RuntimeException("mainLayoutId<0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
        //return super.onCreateView(inflater, container, savedInstanceState);
        //注意！
        return mView;
    }

    /**
     * 把id 转化成view给他return回去，即类似于return mView=setContentView(mainLayoutId)
     * @param inflater 进行复用
     * @param mainLayoutId 通过id 创建view的对象
     * @return
     */
    private View initFragmentView(LayoutInflater inflater,int mainLayoutId) {
        return inflater.from(mContext).inflate(mainLayoutId,null);
    }

    /**
     * 抽象类是没有private的，他需要靠继承来实现，因此需要公开
     * 模板方法 设计模式
     * 就是父类定义一条规则，具体的内容由子类去实现
     */
    public abstract void afterBindView() ;
    //view的依赖注入绑定
    private void bindView(View mView) {
        ButterKnife.bind(this,mView);
    }
}
