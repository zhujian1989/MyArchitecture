package jzhu.com.moduleusers.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import jzhu.com.libbase.base.BaseFragment;
import jzhu.com.moduleusers.R;

public class MainFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.module_user_fragment_main,container,false);
    }
}
