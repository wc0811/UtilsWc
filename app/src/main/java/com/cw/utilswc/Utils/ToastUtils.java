package com.cw.utilswc.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Create By chao.wang on 2017/11/1 16:33
 * <p>
 * email：wc0811@163.com
 * <p>
 * 类描述：1、不可重复提示；2、任意时间显示；3、 不需要单例，单例是有问题的
 * <p>
 * 更改记录：
 */
public class ToastUtils {
    private static long lastShowTime = -1;


    public static void showLongToast(Context context, String pStrMsg) {
        Log.i("CW", "长时间显示信息");
        show(context, pStrMsg, true);
    }

    public static void showToast(Context context, String pStrMsg) {
        Log.i("CW", "正常，短时间显示信息");
        show(context, pStrMsg, false);
    }

    public static void showToast(Context context, String pStrMsg, int pISpace) {
        Log.i("CW", "任意时间显示信息:" + String.valueOf(pISpace));
        if (null == context) {
            Log.i("CW", "context 为空，不显示信息");
            return;
        }
        if (isRepeat(null, 0)) {
            Log.i("CW", "上次显示信息未消失，不显示信息");
            return;
        }
        getToast(context, pStrMsg, null);
        showArbitrarilyToast(getToast(context, pStrMsg, null), pISpace);
    }

    private static Toast getToast(Context context, String pStrMsg, Boolean isLong) {
        if (null == isLong || isLong) {
            return Toast.makeText(context, pStrMsg, Toast.LENGTH_LONG);
        } else {
            return Toast.makeText(context, pStrMsg, Toast.LENGTH_SHORT);
        }
    }

    private static void show(Context context, String pStrMsg, Boolean isLong) {
        if (null == context) {
            Log.i("CW", "context 为空，不显示信息");
            return;
        }
        if (isRepeat(isLong, 0)) {
            Log.i("Toast", "上次显示信息未消失，不显示信息");
            return;
        }
        getToast(context, pStrMsg, isLong).show();
    }

    private static boolean isRepeat(Boolean isLong, int pISpace) {

        long currentTime = System.currentTimeMillis();
        int iSpace;
        if (null == isLong) {
            iSpace = pISpace;
        } else if (isLong) {
            iSpace = 3500;
        } else {
            iSpace = 2000;
        }
        if (currentTime - lastShowTime < iSpace) {
            return true;
        }
        lastShowTime = currentTime;
        return false;
    }

    /**
     * 3.5s ,is Long Enough
     *
     * @param toast
     * @param pISpace
     */
    public static void showArbitrarilyToast(final Toast toast, int pISpace) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3500);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, pISpace);
    }
}
