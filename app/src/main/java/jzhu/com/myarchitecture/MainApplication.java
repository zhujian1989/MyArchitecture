package jzhu.com.myarchitecture;

import io.flutter.view.FlutterMain;
import jzhu.com.libbase.base.BaseApplication;
import jzhu.com.myarchitecture.di.component.DaggerAppComponent;

public class MainApplication extends BaseApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        FlutterMain.startInitialization(this);
    }

    @Override
    protected void injectApp() {
        DaggerAppComponent.builder()
                          .build()
                          .inject(this);
    }

}
