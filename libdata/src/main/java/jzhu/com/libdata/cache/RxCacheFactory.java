package jzhu.com.libdata.cache;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import jzhu.com.libdata.network.RetrofitFactory;

import java.io.File;

public class RxCacheFactory {

    private static volatile RxCacheFactory sInstance;

    private RxCache.Builder rxCachebuilder;

    public static RxCacheFactory getInstance() {
        if (null == sInstance) {
            synchronized (RetrofitFactory.class) {
                if (null == sInstance) {
                    sInstance = new RxCacheFactory();
                }
            }
        }
        return sInstance;
    }

    private RxCacheFactory() {
        rxCachebuilder = new RxCache.Builder();
    }

    public <T> T create(final File file, final Class<T> service) {
        return rxCachebuilder.persistence(file,new GsonSpeaker()).using(service);
    }


}
