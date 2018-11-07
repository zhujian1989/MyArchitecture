package jzhu.com.myarchitecture.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import jzhu.com.myarchitecture.R;
import jzhu.com.myarchitecture.adapter.ViewPagerAdapter;

import java.util.ArrayList;

public class IndicatorViewPager extends FrameLayout {

    private ViewPager viewPager;

    private LinearLayout indicator;

    private Context context;

    private ArrayList<ImageView> dotsList;

    public IndicatorViewPager(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public IndicatorViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public IndicatorViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IndicatorViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(context).inflate(R.layout.os_banner_layout, null);
        viewPager = view.findViewById(R.id.vp);
        indicator = view.findViewById(R.id.indicator);
        addView(view);
    }

    public void bindData(String[] list) {

        initDots(list.length);

        viewPager.setAdapter(new ViewPagerAdapter(list, getContext()));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int p) {
                for (int i = 0; i < dotsList.size(); i++) {
                    if (p % dotsList.size() == i) {
                        dotsList.get(i).setImageResource(R.drawable.circle_selected);
                    }
                    else {
                        dotsList.get(i).setImageResource(R.drawable.circle_normal);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initDots(int size) {
        if(size == 0){
            return;
        }

        dotsList = new ArrayList<ImageView>();

        for (int i = 0; i < size; i++) {
            ImageView view = new ImageView(getContext());
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
}
