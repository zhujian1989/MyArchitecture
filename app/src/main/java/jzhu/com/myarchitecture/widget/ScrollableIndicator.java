package jzhu.com.myarchitecture.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class ScrollableIndicator extends LinearLayout {
    public ScrollableIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    private Scroller scroller;

    public void smoothScrollTo(int destX) {
        int finalX = scroller.getFinalX();
        int deltaX = destX - finalX;
        scroller.startScroll(finalX, 0, deltaX, 0, 300);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            postInvalidate();
        }
    }
}
