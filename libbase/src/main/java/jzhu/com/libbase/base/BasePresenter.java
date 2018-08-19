package jzhu.com.libbase.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends BaseView> {

    private V view;

    private CompositeDisposable compositeDisposable;

    public void onAttachView(V view) {
        this.view = view;
    }

    public void onDetachView() {
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view;
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
