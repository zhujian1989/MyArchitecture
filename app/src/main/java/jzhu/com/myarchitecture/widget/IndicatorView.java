package jzhu.com.myarchitecture.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import jzhu.com.myarchitecture.R;

public class IndicatorView extends FrameLayout {
    private Context mContext;
    private ScrollableIndicator indicator;
    private View indicatorView;
    private int total;
    private int indicatorContentWidth;
    private int indicatorContentHeight;

    public IndicatorView(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public IndicatorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(mContext).inflate(R.layout.indicator_layout, null);
        indicator = (ScrollableIndicator) view.findViewById(R.id.indicator);
        indicatorView = view.findViewById(R.id.indicator_content);
        //壳
        int width = WindowUtil.getWindowWidth(mContext);
        int height = WindowUtil.dip2px(mContext, 10);
        indicatorView.getLayoutParams().width = width;
        indicatorView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        addView(view);
    }

    public void init(int size) {
        int screeWidth = WindowUtil.getWindowWidth(mContext);
        total = size;
        indicatorContentWidth = screeWidth / total;
        indicatorContentHeight = WindowUtil.dip2px(mContext, 10);
        //内容
        indicatorView.getLayoutParams().width = indicatorContentWidth;
        indicatorView.setLayoutParams(new LinearLayout.LayoutParams(indicatorContentWidth, indicatorContentHeight));
    }

    public void smoothSlideTo(int index) {
        if (index > total || index < 0) {
            return;
        }
        indicator.smoothScrollTo(-index * indicatorContentWidth);
    }
}
