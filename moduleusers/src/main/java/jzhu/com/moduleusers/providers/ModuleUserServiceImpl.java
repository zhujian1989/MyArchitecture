package jzhu.com.moduleusers.providers;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import jzhu.com.libprovider.config.RouterPath;
import jzhu.com.libprovider.providers.ModuleUserService;
import jzhu.com.moduleusers.ui.MainFragment;

@Route(path = RouterPath.ModuleUserPath.MAIN_FRAGMENT_PROVIDER,name = "ModuleUserServiceImpl")
public class ModuleUserServiceImpl implements ModuleUserService {

    @Override
    public Fragment getModuleUserFragment() {
        return new MainFragment();
    }

    @Override
    public void init(Context context) {

    }
}
