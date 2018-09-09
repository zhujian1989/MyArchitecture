package jzhu.com.libbase.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.lang.ref.WeakReference;
import java.lang.reflect.Proxy;

public abstract class BasePresenter<V extends BaseView> {

    private WeakReference<V> mViewReference;

    private V mProxyView;

    private CompositeDisposable compositeDisposable;

    public void onAttachView(V view) {

        this.mViewReference = new WeakReference<V>(view);

        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), (proxy, method, args) -> {
            if (mViewReference == null || mViewReference.get() == null) {
                return null;
            }
            else {
                return method.invoke(mViewReference.get(), args);
            }
        });

    }

    public void onDetachView() {
        this.mViewReference.clear();
        this.mViewReference = null ;
        this.mProxyView = null ;
    }

    public V getView() {
        return mProxyView;
    }

    public void addDisposable(Disposable subscription) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(subscription);
    }

    public void unDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

}
