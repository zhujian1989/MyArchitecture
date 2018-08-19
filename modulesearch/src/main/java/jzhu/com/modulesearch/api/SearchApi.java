package jzhu.com.modulesearch.api;

import io.reactivex.Observable;
import jzhu.com.libprovider.model.UserModel;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface SearchApi {

        @GET("/users/{name}/following")
        Observable<List<UserModel>> searchFollowersByName(@Path("name") String name);


}
