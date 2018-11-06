package jzhu.com.myarchitecture.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import jzhu.com.libbase.base.BaseActivity;
import jzhu.com.libprovider.config.RouterPath;
import jzhu.com.libprovider.providers.ModuleSearchService;
import jzhu.com.libprovider.providers.ModuleUserService;
import jzhu.com.myarchitecture.R;
import jzhu.com.myarchitecture.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.top_vp)
    ViewPager topVp;

    @BindView(R.id.indicator)
    LinearLayout indicator;

//    @BindView(R.id.top_vp)
//    IndicatorViewPager topVp;

    @Autowired
    ModuleSearchService moduleSearchService;

    @Autowired
    ModuleUserService moduleUserService;

    private ArrayList<ImageView> dotsList;

    private String[] tabTitles = new String[] { "Module User", "Module Search" };

    private String[] nums = new String[] { "1", "2", "3", "4", "5" };

    private List<Fragment> fragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        initFragments();
        initDot();
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
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(nums, this);
        topVp.setAdapter(viewPagerAdapter);

        topVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int p) {
//遍历小圆点集合
                for (int i = 0; i < dotsList.size(); i++) {
                    if (p % dotsList.size() == i) {//设置当前小圆点
                        dotsList.get(i).setImageResource(R.drawable.circle_selected);
                    }
                    else {//设置其他小圆点
                        dotsList.get(i).setImageResource(R.drawable.circle_normal);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initDot() {

//实例化一个集合存放小圆点
        dotsList = new ArrayList<ImageView>();
        for (int i = 0; i < nums.length; i++) {
            ImageView view = new ImageView(this);
            //把第一个小圆点设置为选中状态
            if (i == 0) {
                view.setImageResource(R.drawable.circle_selected);
            }
            else {
                view.setImageResource(R.drawable.circle_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            //设置小圆点的边距
            params.setMargins(5, 0, 5, 0);
            //把小圆点添加到布局中
            indicator.addView(view, params);
            //把小圆点添加到集合
            dotsList.add(view);
        }

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
