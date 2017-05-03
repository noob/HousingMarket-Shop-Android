package com.carlos.sxl.use.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.app.ActivityCompat;


import com.carlos.sxl.use.util.L;

import java.util.Stack;

/**
 * Created by Dale'
 * On 2016/3/31.
 */
public class AppManager extends Activity {

    private static AppManager instance;
    private static Stack<Activity> activityStack;   //activity栈
    private static String TAG = "AppManager";


    /**
     * 单一实例(保证整个程序中只有一个该类的实例对象)
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        return instance;

    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
        L.d(TAG, "size = " + activityStack.size());
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing() && activityStack != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing())
                ActivityCompat.finishAfterTransition(activity);
//            activity.finish();
            activity = null;
            L.d(TAG, "size = " + activityStack.size());
        }
    }

    /**
     * 移除栈中Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null && activityStack != null) {
            activityStack.remove(activity);
            activity = null;
            L.d(TAG, "size = " + activityStack.size());
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {   //遍历栈中所有的Activity
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 判断activity桟中是否存在指定的activity
     */
    public boolean isActivityExist(Class<?> cls) {
        boolean isActivityExist = false;
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                isActivityExist = true;
            }
        }
        return isActivityExist;
    }

    /**
     * 获取指定的Activity
     */
    public static Activity getActivity(Class<?> cls) {
        if (activityStack != null)
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    /**
     * 退出应用程序
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
