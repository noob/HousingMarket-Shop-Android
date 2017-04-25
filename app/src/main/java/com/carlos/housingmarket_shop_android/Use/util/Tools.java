package com.carlos.housingmarket_shop_android.Use.util;

public class Tools {

    private final static String TAG = "Tools";
    private final static long TIME = 500;
    private static long lastClickTime;


    /**
     * 500毫秒限制点击一次
     */
    public synchronized static boolean isClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < TIME) {
            return false;
        }
        lastClickTime = time;
        return true;
    }

}
