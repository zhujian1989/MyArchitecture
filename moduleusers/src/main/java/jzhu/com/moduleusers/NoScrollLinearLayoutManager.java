package jzhu.com.moduleusers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class NoScrollLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public NoScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
