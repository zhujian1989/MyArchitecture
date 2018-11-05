package jzhu.com.myarchitecture.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import jzhu.com.libbase.base.BaseActivity;
import jzhu.com.libprovider.config.RouterPath;
import jzhu.com.libprovider.providers.ModuleSearchService;
import jzhu.com.libprovider.providers.ModuleUserService;
import jzhu.com.myarchitecture.R;
import jzhu.com.myarchitecture.widget.EnhanceTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    EnhanceTabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Autowired
    ModuleSearchService moduleSearchService;

    @Autowired
    ModuleUserService moduleUserService;

    private String[] tabTitles = new String[] { "Module User", "Module Search"};

    private List<Fragment> fragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        initFragments();
        initTabLayout();
    }

    @Override
    protected boolean injectRouter() {
        return true;
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(moduleUserService.getModuleUserFragment());
        fragments.add(moduleSearchService.getModuleSearchFragment());
    }



    private void initTabLayout() {
        ModuleAdapter adapter = new ModuleAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        for(int i=0;i<tabTitles.length;i++){
            tabLayout.addTab(tabTitles[i]);
        }

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout.getTabLayout()));
        tabLayout.setupWithViewPager(viewPager);

    }


    @OnClick(R.id.float_btn)
    public void onViewClicked() {
        ARouter.getInstance()
               .build(RouterPath.MainPath.MAIN_FLUTTER_CONTAINER)
               .withString("path", RouterPath.ModuleFlutterPath.FLUTTER_HOME)
               .navigation();
    }

    public class ModuleAdapter extends FragmentPagerAdapter {

        public ModuleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
