package jzhu.com.libdata;

import io.reactivex.Observable;
import retrofit2.http.GET;

import java.util.List;

public interface TestApi {

    @GET("/users")
    Observable<List<TestModel>> getUsers();

}
