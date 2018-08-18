package jzhu.com.libprovider.providers;

import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface ModuleUserService extends IProvider {

    Fragment getModuleUserFragment();

}
