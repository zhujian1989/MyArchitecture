package jzhu.com.libbase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jzhu.com.libbase.util.ToastUtils;

import javax.inject.Inject;

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseInjectFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected View mRootView;

    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onAttachView(this);
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mPresenter.onDetachView();
        mPresenter.unDisposable();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(msg);
    }

}
