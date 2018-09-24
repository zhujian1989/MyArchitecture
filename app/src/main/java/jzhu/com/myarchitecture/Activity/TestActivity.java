package jzhu.com.myarchitecture.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import jzhu.com.libbase.base.BaseActivity;
import jzhu.com.libprovider.config.RouterPath;
import jzhu.com.myarchitecture.R;

@Route(path = RouterPath.MainPath.MAIN_TEST, name = "TestActivity")
public class TestActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        toolbar.setTitle("TestActivity");
    }


    @OnClick(R.id.btn)
    public void onViewClicked() {
        ARouter.getInstance()
               .build(RouterPath.MainPath.MAIN_FLUTTER_CONTAINER)
               .withString("path", RouterPath.ModuleFlutterPath.FLUTTER_TEST)
               .navigation();
    }
}
