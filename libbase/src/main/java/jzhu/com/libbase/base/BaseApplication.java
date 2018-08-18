package jzhu.com.libbase.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.alibaba.android.arouter.launcher.ARouter;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }

    private boolean isDebug() {
        return true;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
