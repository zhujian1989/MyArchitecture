package jzhu.com.modulesearch.providers;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import io.reactivex.Observable;
import jzhu.com.libprovider.config.RouterPath;
import jzhu.com.libprovider.model.UserModel;
import jzhu.com.libprovider.providers.ModuleSearchService;
import jzhu.com.modulesearch.Repository.SearchRepositoryImpl;
import jzhu.com.modulesearch.ui.MainFragment;

import java.util.List;

@Route(path = RouterPath.ModuleSearchPath.MAIN_FRAGMENT_PROVIDER,name = "ModuleSearchServiceImpl")
public class ModuleSearchServiceImpl implements ModuleSearchService {

    @Override
    public Fragment getModuleSearchFragment() {
        return new MainFragment();
    }

    @Override
    public Observable<List<UserModel>> searchFollowersByName(String name) {
        return new SearchRepositoryImpl().searchFollowersByName(name);
    }

    @Override
    public void init(Context context) {

    }
}
