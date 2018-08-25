package jzhu.com.libbase.base;

import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jzhu.com.libbase.util.ToastUtils;

import javax.inject.Inject;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseInjectActivity implements BaseView {

    @Inject
    protected T mPresenter;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initContentView(savedInstanceState);
        mPresenter.onAttachView(this);
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
    protected void onDestroy() {
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
