package jzhu.com.moduleusers.cache;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;
import io.rx_cache2.ProviderKey;
import jzhu.com.libprovider.model.UserModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface UsersCacheProviders {

    @ProviderKey("Users")
    @LifeCache(duration = 1,timeUnit = TimeUnit.MINUTES)
    Observable<List<UserModel>> getUsers(Observable<List<UserModel>> oMocks);

}
