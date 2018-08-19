package jzhu.com.moduleusers;

import jzhu.com.libbase.base.BaseApplication;
import jzhu.com.moduleusers.di.componet.DaggerModuleUserComponet;

public class ModuleUserApplication extends BaseApplication {
    @Override
    protected void injectApp() {
        DaggerModuleUserComponet.builder().build().inject(this);
    }
}
