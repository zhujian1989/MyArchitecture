package jzhu.com.moduleusers.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import jzhu.com.libbase.base.BaseActivity;
import jzhu.com.moduleusers.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_user_activity_main);
    }
}
