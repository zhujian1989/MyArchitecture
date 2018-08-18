package jzhu.com.libdata;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jzhu.com.libdata.network.RetrofitFactory;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class NetworkSimpleTest {

    public static final String TAG = "NetworkSimpleTest";

    @Rule
    public RxJavaRule rxRule = new RxJavaRule();

    @Rule
    public MethodNameRule methodNameExample = new MethodNameRule();

    @Test
    public void retrofitResp() {

        RetrofitFactory.getInstance().create(TestApi.class).getUsers().subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(new Consumer<List<TestModel>>() {
            @Override
            public void accept(List<TestModel> testModels) throws Exception {
                for(TestModel model : testModels){
                    System.out.println(model.getAvatar_url());
                }
            }
        });
    }

}
