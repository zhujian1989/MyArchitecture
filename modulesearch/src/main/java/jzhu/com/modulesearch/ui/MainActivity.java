package jzhu.com.modulesearch.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import jzhu.com.libbase.base.BaseActivity;
import jzhu.com.modulesearch.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_search_activity_main);
    }
}
