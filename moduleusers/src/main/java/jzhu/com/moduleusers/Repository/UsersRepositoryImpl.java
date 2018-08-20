package jzhu.com.moduleusers.Repository;

import io.reactivex.Observable;
import jzhu.com.libdata.network.RetrofitFactory;
import jzhu.com.libprovider.model.UserModel;
import jzhu.com.moduleusers.api.UsersApi;

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
