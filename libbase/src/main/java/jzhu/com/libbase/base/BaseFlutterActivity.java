package jzhu.com.libbase.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.alibaba.android.arouter.launcher.ARouter;
import io.flutter.app.FlutterActivityDelegate;
import io.flutter.app.FlutterActivityEvents;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;
import jzhu.com.libbase.plugin.FlutterPluginJumpToAct;

public abstract class BaseFlutterActivity extends AppCompatActivity implements FlutterView.Provider, PluginRegistry, FlutterActivityDelegate.ViewFactory {

    private Unbinder mUnbinder;

    private final FlutterActivityDelegate delegate = new FlutterActivityDelegate(this, this);
    private final FlutterActivityEvents eventDelegate;
    private final FlutterView.Provider viewProvider;
    private final PluginRegistry pluginRegistry;


    public BaseFlutterActivity() {
        this.eventDelegate = this.delegate;
        this.viewProvider = this.delegate;
        this.pluginRegistry = this.delegate;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (injectRouter())
            ARouter.getInstance().inject(this);
        this.eventDelegate.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initContentView(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
        registerCustomPlugin(this);
    }

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     *
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    //处理bundle数据
    protected abstract void initContentView(Bundle savedInstanceState);

    protected boolean injectRouter() {
        return false;
    }

    public FlutterView getFlutterView() {
        return this.viewProvider.getFlutterView();
    }

    public FlutterView createFlutterView(Context context) {
        return null;
    }

    public FlutterNativeView createFlutterNativeView() {
        return null;
    }

    public boolean retainFlutterNativeView() {
        return false;
    }

    public final boolean hasPlugin(String key) {
        return this.pluginRegistry.hasPlugin(key);
    }

    public final <T> T valuePublishedByPlugin(String pluginKey) {
        return this.pluginRegistry.valuePublishedByPlugin(pluginKey);
    }

    public final Registrar registrarFor(String pluginKey) {
        return this.pluginRegistry.registrarFor(pluginKey);
    }

    protected void onStart() {
        super.onStart();
        this.eventDelegate.onStart();
    }

    protected void onResume() {
        super.onResume();
        this.eventDelegate.onResume();
    }

    protected void onDestroy() {
        this.eventDelegate.onDestroy();
        mUnbinder.unbind();
        super.onDestroy();
    }

    public void onBackPressed() {
        if (!this.eventDelegate.onBackPressed()) {
            super.onBackPressed();
        }

    }

    protected void onStop() {
        this.eventDelegate.onStop();
        super.onStop();
    }

    protected void onPause() {
        super.onPause();
        this.eventDelegate.onPause();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.eventDelegate.onPostResume();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        this.eventDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!this.eventDelegate.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    protected void onNewIntent(Intent intent) {
        this.eventDelegate.onNewIntent(intent);
    }

    public void onUserLeaveHint() {
        this.eventDelegate.onUserLeaveHint();
    }

    public void onTrimMemory(int level) {
        this.eventDelegate.onTrimMemory(level);
    }

    public void onLowMemory() {
        this.eventDelegate.onLowMemory();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.eventDelegate.onConfigurationChanged(newConfig);
    }

    private static void registerCustomPlugin(PluginRegistry registrar) {

        FlutterPluginJumpToAct.registerWith(registrar.registrarFor(FlutterPluginJumpToAct.CHANNEL));

    }
}
