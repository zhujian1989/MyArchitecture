package jzhu.com.moduleusers.cache;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import jzhu.com.libbase.base.BaseApplication;

public class UsersCacheProviders {

    private static UsersProviders usersProviders;

    public synchronized static UsersProviders getUserCache() {
        if (usersProviders == null) {
            usersProviders = new RxCache.Builder()
                    .persistence(BaseApplication.getInstance().getApplicationContext().getExternalCacheDir(), new GsonSpeaker())//缓存文件的配置、数据的解析配置
                    .using(UsersProviders.class);//这些配置对应的缓存接口
        }
        return usersProviders;
    }

}
