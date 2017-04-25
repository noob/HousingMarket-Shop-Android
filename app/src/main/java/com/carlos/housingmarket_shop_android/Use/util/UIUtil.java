package com.carlos.housingmarket_shop_android.Use.util;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.carlos.housingmarket_shop_android.R;

import java.lang.reflect.Field;

public class UIUtil {

    /**
     * 获取状态栏的高度
     * @return
     */
    public static int getStatusBarHeight() {
        Class<?> c;
        Object obj;
        Field field;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return x;
    }

    /**
     * 如果系统为4.4以上 ，则隐藏状态栏
     * @param activity activity
     * @param resource 资源文件整体的 layout
     */
    public static void hideTitleBar(Activity activity, int resource) {
        if (Build.VERSION.SDK_INT > 18) {  // 如果系统为4.4以上 ，则隐藏状态栏
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            LinearLayout whole_layout = (LinearLayout) activity.findViewById(resource);  // 页面整体布局
            whole_layout.setPadding(0, activity.getResources().getDimensionPixelSize(getStatusBarHeight()), 0, 0);
        }
    }

    /**
     * 如果系统为4.4以上 ，则隐藏状态栏
     *
     * @param activity activity
     * @param resource 资源文件整体的 layout
     */
    public static void hideTitleBar_2(Activity activity, int resource, int rescourse2) {
        if (Build.VERSION.SDK_INT > 18) {  // 如果系统为4.4以上 ，则隐藏状态栏
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            RelativeLayout whole_layout = (RelativeLayout) activity.findViewById(resource);  // 页面整体布局
            whole_layout.setPadding(0, activity.getResources().getDimensionPixelSize(getStatusBarHeight()), 0, 0);
            RelativeLayout whole_layout2 = (RelativeLayout) activity.findViewById(rescourse2);  // 页面整体布局
            whole_layout2.setPadding(0, activity.getResources().getDimensionPixelSize(getStatusBarHeight()), 0, 0);
        }
    }

    /**
     * 状态栏的颜色 为  固定颜色
     */
    public static void steepToolBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            //此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(R.color.theme_color);
        }
    }

    /**
     * 状态栏的颜色 为 指定颜色
     */
    public static void steepToolBar(Activity activity, int colorResource) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            //此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(colorResource);
        }
    }

}
