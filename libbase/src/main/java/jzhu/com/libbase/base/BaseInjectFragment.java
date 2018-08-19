package jzhu.com.libbase.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.launcher.ARouter;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseInjectFragment extends Fragment {

    protected void inject() {
        AndroidSupportInjection.inject(this);
        if (injectRouter())
            ARouter.getInstance().inject(this);
    }

    protected boolean injectRouter() {
        return false;
    }

    @Override
    public void onAttach(Context context) {
        inject();
        super.onAttach(context);
    }
}
