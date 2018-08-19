package jzhu.com.myarchitecture;

import jzhu.com.libbase.base.BaseApplication;
import jzhu.com.myarchitecture.di.component.DaggerAppComponent;

public class MainApplication extends BaseApplication {

    @Override
    protected void injectApp() {
        DaggerAppComponent.builder().build().inject(this);
    }
}
