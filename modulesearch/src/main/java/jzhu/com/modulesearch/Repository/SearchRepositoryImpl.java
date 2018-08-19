package jzhu.com.modulesearch.Repository;

import io.reactivex.Observable;
import jzhu.com.libdata.network.RetrofitFactory;
import jzhu.com.libprovider.model.UserModel;
import jzhu.com.modulesearch.api.SearchApi;

import javax.inject.Inject;
import java.util.List;

public class SearchRepositoryImpl implements SearchRepository {

    @Inject
    public SearchRepositoryImpl() {
    }

    @Override
    public Observable<List<UserModel>> searchFollowersByName(String name) {
        return RetrofitFactory.getInstance().create(SearchApi.class).searchFollowersByName(name);
    }
}
