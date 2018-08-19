package jzhu.com.libprovider.providers;

import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.facade.template.IProvider;
import io.reactivex.Observable;
import jzhu.com.libprovider.model.UserModel;

import java.util.List;

public interface ModuleSearchService extends IProvider {

    Fragment getModuleSearchFragment();

    Observable<List<UserModel>> searchFollowersByName(String name);
}
