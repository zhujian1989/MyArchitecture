package jzhu.com.libbase.plugin;

import android.app.Activity;
import com.alibaba.android.arouter.launcher.ARouter;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class FlutterPluginJumpToAct implements MethodChannel.MethodCallHandler {

    public static String CHANNEL = "com.jzhu.jump/plugin";

    static MethodChannel channel;

    private Activity activity;

    private FlutterPluginJumpToAct(Activity activity) {
        this.activity = activity;
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        channel = new MethodChannel(registrar.messenger(), CHANNEL);
        FlutterPluginJumpToAct instance = new FlutterPluginJumpToAct(registrar.activity());
        //setMethodCallHandler在此通道上接收方法调用的回调
        channel.setMethodCallHandler(instance);
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {

        if (call.method.equals("jump2act")) {

            //来自flutter的数据
            String path = call.argument("path");

            //跳转到指定Activity
            ARouter.getInstance().build(path).navigation();

            //返回给flutter的参数
            result.success("success");
        }

        else {
            result.notImplemented();
        }
    }

}
