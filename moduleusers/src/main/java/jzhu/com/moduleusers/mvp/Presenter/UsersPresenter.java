package jzhu.com.moduleusers.mvp.Presenter;

import io.reactivex.Observable;
import jzhu.com.libbase.base.BasePresenter;
import jzhu.com.libbase.util.RxUtil;
import jzhu.com.libprovider.model.UserModel;
import jzhu.com.moduleusers.Repository.UsersRepository;
import jzhu.com.moduleusers.cache.UsersCacheProviders;
import jzhu.com.moduleusers.mvp.view.UsersView;

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
        
        Observable<List<UserModel>> users = usersRepository.getUsers();

        UsersCacheProviders.getUserCache().getUsers(users).
                doOnSubscribe(disposable -> {
                    addDisposable(disposable);
                    usersView.showLoading();

                }).compose(RxUtil.io2main())
                           .subscribe(userModels -> {
                               usersView.getUsersSuc(userModels);
                               usersView.hideLoading();
                           }, throwable -> {
                               usersView.getUsersFail(throwable);
                               usersView.hideLoading();
                           });
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        unDisposable();
    }

}
