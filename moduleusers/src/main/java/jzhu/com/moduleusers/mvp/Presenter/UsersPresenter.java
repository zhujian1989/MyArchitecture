package jzhu.com.moduleusers.mvp.Presenter;

import io.reactivex.Observable;
import jzhu.com.libbase.base.BaseApplication;
import jzhu.com.libbase.base.BasePresenter;
import jzhu.com.libbase.util.RxUtil;
import jzhu.com.libdata.cache.RxCacheFactory;
import jzhu.com.libprovider.model.UserModel;
import jzhu.com.moduleusers.Repository.UsersRepository;
import jzhu.com.moduleusers.cache.UsersCacheProviders;
import jzhu.com.moduleusers.mvp.view.UsersView;

import javax.inject.Inject;
import java.util.List;

public class UsersPresenter extends BasePresenter<UsersView> {

    @Inject
    UsersRepository usersRepository;

    @Inject
    public UsersPresenter() {

    }

    public void getUsers() {

        Observable<List<UserModel>> users = usersRepository.getUsers();

        RxCacheFactory.getInstance().create(BaseApplication.getInstance().getCacheDir(), UsersCacheProviders.class).getUsers(users).
                doOnSubscribe(disposable -> {
                    addDisposable(disposable);
                    getView().showLoading();

                }).compose(RxUtil.io2main())
                      .subscribe(userModels -> {
                          getView().getUsersSuc(userModels);
                          getView().hideLoading();
                      }, throwable -> {
                          getView().getUsersFail(throwable);
                          getView().hideLoading();
                      });
    }

}
