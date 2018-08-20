package jzhu.com.libbase;

import io.reactivex.Observable;
import jzhu.com.libdata.network.HttpResponse;
import retrofit2.http.GET;

import java.util.List;

public interface TestApi {

    @GET("banner/json")
    Observable<HttpResponse<List<TestModel>>> getUsers();

}
