package jzhu.com.libbase.base;

public interface BaseView {

    void showLoading();

    void hideLoading();

    void onError(String msg);

    void onSuccess(String msg);

    void showToast(String msg);

}
