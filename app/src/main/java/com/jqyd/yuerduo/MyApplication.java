package com.jqyd.yuerduo;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;

import com.jqyd.yuerduo.util.FileLogTool;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.LogTool;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.PlatformConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * Created by zhangfan on 15-12-30.
 */
public class MyApplication extends Application {

    public static final String ReleaseTag = "yuerduo_debug";


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
                .logTool(new FileLogTool(this)); // custom log tool, optional

        TypefaceCollection typeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/HYQH.ttf"))
                .create();
        TypefaceHelper.init(typeface);


        PlatformConfig.setSinaWeibo("2117222028", "1468577ab748b2d87d6cdc92ac9e9da8");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        PlatformConfig.setTencentWeibo("", "");
    }



}
