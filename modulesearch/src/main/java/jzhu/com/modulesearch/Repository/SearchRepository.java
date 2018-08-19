package jzhu.com.modulesearch.Repository;

import io.reactivex.Observable;
import jzhu.com.libprovider.model.UserModel;

import java.util.List;

public interface SearchRepository {

    Observable<List<UserModel>> searchFollowersByName(String name);

}
