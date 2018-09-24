package jzhu.com.myarchitecture.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.StringCodec;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;
import jzhu.com.libbase.base.CustomFlutterActivity;
import jzhu.com.libbase.util.ToastUtils;
import jzhu.com.libprovider.config.RouterPath;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Route(path = RouterPath.MainPath.MAIN_FLUTTER_CONTAINER, name = "FlutterContainerActivity")
public class FlutterContainerActivity extends CustomFlutterActivity {

    private static String CHANNEL = "com.jzhu.msg/plugin";

    private static String CHANNEL_BINARY = "com.jzhu.msg.binary/plugin";

    private static int TIME_ONE_SECOND = 1000;

    @Autowired(name = "path")
    String path;

    @Override
    protected boolean injectRouter() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBasicMessageChannel();
    }

    @Override
    public FlutterView createFlutterView(Context context) {
        WindowManager.LayoutParams matchParent = new WindowManager.LayoutParams(-1, -1);
        FlutterNativeView nativeView = this.createFlutterNativeView();
        FlutterView flutterView = new FlutterView(FlutterContainerActivity.this, (AttributeSet) null, nativeView);
        flutterView.setInitialRoute(path);
        flutterView.setLayoutParams(matchParent);
        this.setContentView(flutterView);
        return flutterView;
    }

    private void initBasicMessageChannel() {
        switch (path) {
            case RouterPath.ModuleFlutterPath.FLUTTER_HOME:
                initHomeBasicMessage();
                break;
            case RouterPath.ModuleFlutterPath.FLUTTER_TEST:
                initTestBasicMessage();
                break;
        }

    }


    private void initTestBasicMessage() {

        BasicMessageChannel channel = new BasicMessageChannel<String>(
                getFlutterView(), CHANNEL, StringCodec.INSTANCE);
        channel.setMessageHandler((o, reply) -> {
            ToastUtils.show((String)o,3000);
            reply.reply("FlutterContainerActivity：回条消息给你");
        });
        new Handler().postDelayed(() -> channel.send("FlutterContainerActivity：发条消息给你"), TIME_ONE_SECOND);

        ByteBuffer message = ByteBuffer.allocateDirect(256);
        message.putDouble(3.14);
        message.putInt(123456789);
        new Handler().postDelayed(() -> getFlutterView().send(CHANNEL_BINARY,message), TIME_ONE_SECOND);
        getFlutterView().setMessageHandler(CHANNEL_BINARY, (byteBuffer, binaryReply) -> {
            byteBuffer.order(ByteOrder.nativeOrder());
            double x = byteBuffer.getDouble();
            int n = byteBuffer.getInt();
            Log.i("zj", "Received: "+x+ " and "+ n);
            binaryReply.reply(message);
        });

    }

    private void initHomeBasicMessage() {
        //todo
    }

}
