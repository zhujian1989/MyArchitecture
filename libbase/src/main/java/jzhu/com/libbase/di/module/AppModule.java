package jzhu.com.libbase.di.module;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import jzhu.com.libbase.base.BaseApplication;
import jzhu.com.libbase.di.PerScoped;

@Module
public class AppModule {

    @PerScoped
    @Provides
    Application provideContext(BaseApplication application) {
        return application;
    }


}
