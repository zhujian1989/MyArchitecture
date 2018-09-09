package jzhu.com.moduleusers.di.componet;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import jzhu.com.libbase.base.BaseApplication;
import jzhu.com.libbase.di.PerScoped;
import jzhu.com.libbase.di.module.AppModule;
import jzhu.com.moduleusers.di.module.UserAllActivityModule;
import jzhu.com.moduleusers.di.module.UserAllFragmentModule;

@PerScoped
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        UserAllFragmentModule.class,
        UserAllActivityModule.class})
public interface ModuleUserComponet {

    void inject(BaseApplication baseApplication);

}
