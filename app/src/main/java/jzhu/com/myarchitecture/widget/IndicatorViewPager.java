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
import jzhu.com.myarchitecture.R;
import jzhu.com.myarchitecture.adapter.ViewPagerAdapter;

public class IndicatorViewPager extends FrameLayout {

    private ViewPager viewPager;

    public IndicatorViewPager(@NonNull Context context) {
        super(context);
    }

    public IndicatorViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IndicatorViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IndicatorViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_with_indicator_layout, null);
        viewPager = view.findViewById(R.id.viewpager);
    }


    public void bindData(String[] list){
        viewPager.setAdapter(new ViewPagerAdapter(list,getContext()));
    }

}
