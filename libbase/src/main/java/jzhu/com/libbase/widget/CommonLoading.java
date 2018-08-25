package jzhu.com.libbase.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import jzhu.com.libbase.R;

public class CommonLoading extends Dialog {

    private static CommonLoading dialog;

    public CommonLoading(Context context) {
        super(context);
    }

    public CommonLoading(Context context, int theme) {
        super(context);
    }

    public static CommonLoading createLoading(Context context) {
        if (dialog == null) {
            dialog = new CommonLoading(context, R.style.loading_style);
        }
        dialog.setTitle("");
        dialog.setContentView(R.layout.common_loading);

        // 按返回键是否取消
        dialog.setCancelable(false);
        // 设置居中
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        // 设置背景层透明度
        lp.dimAmount = 0.1f;
        dialog.getWindow().setAttributes(lp);

        return dialog;
    }

}
