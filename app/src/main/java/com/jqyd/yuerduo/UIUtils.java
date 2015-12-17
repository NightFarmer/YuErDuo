package com.jqyd.yuerduo;

import android.app.Activity;
import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhangfan on 2015/12/14.
 */
public class UIUtils {

    /** 只支持MIUI V6
     * @param context
     * @param type 0--只需要状态栏透明 1-状态栏透明且黑色字体 2-清除黑色字体
     */
    public static void setStatusBarTextColor(Activity context,int type){

        Window window = context.getWindow();
        Class clazz = window.getClass();
        try {
            int tranceFlag = 0;
            int darkModeFlag = 0;
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");
            tranceFlag = field.getInt(layoutParams);
            field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            if (type == 0){
                extraFlagField.invoke(window, tranceFlag, tranceFlag);//只需要状态栏透明
            }else if(type == 1){
                extraFlagField.invoke(window, tranceFlag | darkModeFlag, tranceFlag | darkModeFlag);//状态栏透明且黑色字体
            }else {
                extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
            }
        }catch (Exception e){

        }
    }

    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
}
