package jzhu.com.libbase.di.component;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import jzhu.com.libbase.base.BaseApplication;
import jzhu.com.libbase.di.PerScoped;
import jzhu.com.libbase.di.module.AppModule;

@PerScoped
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class })
public interface BaseAppComponent {

    void inject(BaseApplication baseApplication);

}
