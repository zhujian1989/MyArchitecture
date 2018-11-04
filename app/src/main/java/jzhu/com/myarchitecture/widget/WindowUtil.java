package jzhu.com.myarchitecture.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/**
 * @see #getWindowWidth(Context)
 * @see #getWindowHeight(Context)
 */
public class WindowUtil {

    private static DisplayMetrics sDisplayMetrics;
    private static int sStatusBarHeight;
    private static int sNavigationBarHeight;

    /**
     * get window width of pixels
     *
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context) {
        ensureMetrics(context);
        return sDisplayMetrics.widthPixels;
    }

    /**
     * get window height of pixels
     *
     * @param context
     * @return
     */
    public static int getWindowHeight(Context context) {
        ensureMetrics(context);
        return sDisplayMetrics.heightPixels;
    }

    public static float getDensity(Context context) {
        ensureMetrics(context);
        return sDisplayMetrics.density;
    }

    /**
     * Make sure DisplayMetrics is valid for Locker (screen height > screen width)
     *
     * @param context
     */
    private static void ensureMetrics(Context context) {
        if (sDisplayMetrics == null || sDisplayMetrics.widthPixels > sDisplayMetrics.heightPixels) {
            WindowManager windowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            sDisplayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(sDisplayMetrics);
        }
    }

//    public static int getStatusBarHeight(Context context) {
//        if (sStatusBarHeight <= 0) {
//            final Resources resources = context.getResources();
//            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
//            if (resourceId <= 0) {
//                resourceId = R.dimen.status_bar_height;
//            }
//            sStatusBarHeight = resources.getDimensionPixelSize(resourceId);
//        }
//        return sStatusBarHeight;
//    }
//
//    public static int getNavigationBarHeight(Context context) {
//        if (sNavigationBarHeight <= 0) {
//            final Resources resources = context.getResources();
//            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
//            if (resourceId <= 0) {
//                resourceId = R.dimen.navigation_bar_height;
//            }
//            sNavigationBarHeight = resources.getDimensionPixelSize(resourceId);
//        }
//        return sNavigationBarHeight;
//    }

    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    public static void controlKeyboardLayout(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int[] loc = getLocation(scrollToView);
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInvisibleHeight > 100) {
                    int[] location = new int[2];
                    //获取scrollToView在窗体的坐标
                    scrollToView.getLocationInWindow(location);
                    //计算root滚动高度，使scrollToView在可见区域
                    int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    if (rootInvisibleHeight > root.getRootView().getHeight() - loc[1] - loc[3]) {
                        root.scrollTo(0, srollHeight);
                    }
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }

    /**
     * 获取view宽，高及屏幕上的坐标
     *
     * @param v
     * @return
     */
    public static int[] getLocation(View v) {
        int[] loc = new int[6];
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        loc[0] = location[0];
        loc[1] = location[1];
        loc[2] = v.getWidth();
        loc[3] = v.getHeight();
        loc[4] = location[0] + v.getWidth() / 2;
        loc[5] = location[1] + v.getHeight() / 2;
        return loc;
    }

    /**
     * get status height
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                                               .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
