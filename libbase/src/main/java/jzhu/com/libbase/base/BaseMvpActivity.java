package jzhu.com.libbase.base;

import android.os.Bundle;
import jzhu.com.libbase.util.ToastUtils;

import javax.inject.Inject;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseInjectActivity implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initContentView(savedInstanceState);
        mPresenter.onAttachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
