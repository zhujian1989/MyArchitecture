package jzhu.com.moduleusers.Repository;

import io.reactivex.Observable;
import jzhu.com.libdata.network.RetrofitFactory;
import jzhu.com.moduleusers.api.UsersApi;
import jzhu.com.moduleusers.model.UserModel;

import javax.inject.Inject;
import java.util.List;

public class UsersRepositoryImpl implements UsersRepository {

    @Inject
    public UsersRepositoryImpl() {
    }

    @Override
    public Observable<List<UserModel>> getUsers() {
        return RetrofitFactory.getInstance().create(UsersApi.class).getUsers();
    }

}
