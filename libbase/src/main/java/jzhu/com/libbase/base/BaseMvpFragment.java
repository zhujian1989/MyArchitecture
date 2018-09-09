package jzhu.com.libbase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import jzhu.com.libbase.util.ToastUtils;

import javax.inject.Inject;

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseInjectFragment implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onAttachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetachView();
        mPresenter.unDisposable();
    }

    @Override
    public void showLoading() {
        getActivity().runOnUiThread(() -> mCommonLoading.show());
    }

    @Override
    public void hideLoading() {
        getActivity().runOnUiThread(() -> mCommonLoading.dismiss());
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
