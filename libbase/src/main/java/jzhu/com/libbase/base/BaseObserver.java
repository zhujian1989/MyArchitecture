package jzhu.com.libbase.base;

import android.accounts.NetworkErrorException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import jzhu.com.libdata.network.HttpConfig;
import jzhu.com.libdata.network.HttpResponse;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;


public abstract class BaseObserver<T> implements Observer<HttpResponse<T>> {

    protected BaseView view;

    public BaseObserver(BaseView view) {
        this.view = view;
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(HttpResponse<T> httpResponse) {
        onRequestEnd();
        if (httpResponse.getErrorCode() == HttpConfig.CODE_0) {
            try {
                onSuccees(httpResponse);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                onCodeError(httpResponse);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                || e instanceof TimeoutException
                || e instanceof NetworkErrorException
                || e instanceof UnknownHostException) {
                onFailure(e, true);
            }
            else {
                onFailure(e, false);
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
        onRequestEnd();
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(HttpResponse<T> t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(HttpResponse<T> t) throws Exception {
    }

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected  void onFailure(Throwable e, boolean isNetWorkError) throws Exception{};

    protected void onRequestStart() {
        view.showLoading();
    }

    protected void onRequestEnd() {
        view.hideLoading();
    }
}

