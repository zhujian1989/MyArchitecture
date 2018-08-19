package jzhu.com.myarchitecture.di.component;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import jzhu.com.libbase.base.BaseApplication;
import jzhu.com.libbase.di.module.AppModule;
import jzhu.com.myarchitecture.di.PerScoped;
import jzhu.com.myarchitecture.di.module.AllActivitiesModule;
import jzhu.com.myarchitecture.di.module.AllFragmentsModule;

@PerScoped
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        AllFragmentsModule.class,
        AllActivitiesModule.class})
public interface AppComponent {

    void inject(BaseApplication baseApplication);

}
