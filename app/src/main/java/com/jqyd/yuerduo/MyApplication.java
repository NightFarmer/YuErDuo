package com.jqyd.yuerduo;

import android.app.Application;
import android.graphics.Typeface;

import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by zhangfan on 15-12-30.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger
                .init("鱼儿多")                 // default PRETTYLOGGER or use just init()
//                .methodCount(3)                 // default 2
//                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
//                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
//                .methodOffset(2)                // default 0
                .logTool(new AndroidLogTool()); // custom log tool, optional

        TypefaceCollection typeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/HYQH.ttf"))
                .create();
        TypefaceHelper.init(typeface);
    }


}
