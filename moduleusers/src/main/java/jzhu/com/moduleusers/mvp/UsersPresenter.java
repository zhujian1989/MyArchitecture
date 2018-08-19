package jzhu.com.moduleusers.mvp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jzhu.com.libbase.base.BasePresenter;
import jzhu.com.moduleusers.Repository.UsersRepository;
import jzhu.com.libprovider.model.UserModel;

import javax.inject.Inject;
import java.util.List;

public class UsersPresenter extends BasePresenter<UsersView> {

    @Inject
    UsersRepository usersRepository;

    UsersView usersView;

    @Inject
    public UsersPresenter() {

    }

    @Override
    public void onAttachView(UsersView view) {
        usersView = view;
    }

    public void getUsers() {
        usersRepository.getUsers().doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                addDisposable(disposable);
                usersView.showLoading();

            }
        }).subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Consumer<List<UserModel>>() {
                           @Override
                           public void accept(List<UserModel> userModels) throws Exception {
                                usersView.getUsersSuc(userModels);
                                usersView.hideLoading();
                           }
                       }, new Consumer<Throwable>() {
                           @Override
                           public void accept(Throwable throwable) throws Exception {
                                usersView.getUsersFail(throwable);
                                usersView.hideLoading();
                           }
                       });
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        unDisposable();
    }
}
