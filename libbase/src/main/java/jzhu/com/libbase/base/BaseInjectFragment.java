package jzhu.com.libbase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.alibaba.android.arouter.launcher.ARouter;
import dagger.android.support.AndroidSupportInjection;
import jzhu.com.libbase.widget.CommonLoading;

public abstract class BaseInjectFragment extends Fragment {


    protected View mRootView;

    private Unbinder mUnbinder;

    protected CommonLoading mCommonLoading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
        mCommonLoading = CommonLoading.createLoading(getContext());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            mUnbinder = ButterKnife.bind(this, mRootView);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        initContentView(savedInstanceState);
        return mRootView;
    }


    protected void inject() {
        AndroidSupportInjection.inject(this);
        if (injectRouter())
            ARouter.getInstance().inject(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    protected boolean injectRouter() {
        return false;
    }


    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     *
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    //处理bundle数据
    protected abstract void initContentView(Bundle savedInstanceState);

}
