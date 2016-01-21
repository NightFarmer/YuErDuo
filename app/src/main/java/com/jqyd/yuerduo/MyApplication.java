package com.jqyd.yuerduo;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;

import com.jqyd.yuerduo.bean.FunctionBean;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private ArrayList<FunctionBean> allFunction;

    public ArrayList<FunctionBean> getAllFunctionCopies(){
        ArrayList<FunctionBean> beanArrayList;
        beanArrayList = new ArrayList<>();
        FunctionBean functionBean = new FunctionBean();
        functionBean.icon = R.drawable.grbg;
        functionBean.title = "个人办公";
        beanArrayList.add(functionBean);

        List<FunctionBean> children = new ArrayList<>();
        FunctionBean functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.xxtz;
        functionBean1.title = "消息通知";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.wddd;
        functionBean1.title = "我的订单";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.wdkh;
        functionBean1.title = "我的客户";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.wdysk;
        functionBean1.title = "我的应收款";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.wdskd;
        functionBean1.title = "我的收款单";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.wdshd;
        functionBean1.title = "我的送货单";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.ydkc;
        functionBean1.title = "我的移动库存";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.wdxxfk;
        functionBean1.title = "我的信息反馈";
        children.add(functionBean1);
        functionBean.children = children;


        functionBean = new FunctionBean();
        functionBean.icon = R.drawable.xtbg;
        functionBean.title = "协同办公";
        beanArrayList.add(functionBean);

        children = new ArrayList<>();
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function3;
        functionBean1.title = "公告通知";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.xxfk;
        functionBean1.title = "信息反馈";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.xtxx;
        functionBean1.title = "系统信息";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.ghsxx;
        functionBean1.title = "供货商信息";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.qdfkxx;
        functionBean1.title = "渠道反馈信息";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.zyfkxx;
        functionBean1.title = "职员反馈信息";
        children.add(functionBean1);
        functionBean.children = children;


        functionBean = new FunctionBean();
        functionBean.icon = R.drawable.xsgl;
        functionBean.title = "销售管理";
        beanArrayList.add(functionBean);

        children = new ArrayList<>();
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.xsddgl;
        functionBean1.title = "销售订单管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.xsddtj;
        functionBean1.title = "销售订单统计";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.xssptj;
        functionBean1.title = "销售商品统计";
        children.add(functionBean1);
        functionBean.children = children;


        functionBean = new FunctionBean();
        functionBean.icon = R.drawable.cggl;
        functionBean.title = "采购管理";
        beanArrayList.add(functionBean);

        children = new ArrayList<>();
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.cgddgl;
        functionBean1.title = "采购订单管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.cgddtj;
        functionBean1.title = "采购订单统计";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.cgsptj;
        functionBean1.title = "采购商品统计";
        children.add(functionBean1);
        functionBean.children = children;


        functionBean = new FunctionBean();
        functionBean.icon = R.drawable.cwgl;
        functionBean.title = "财务管理";
        beanArrayList.add(functionBean);

        children = new ArrayList<>();
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.skgl;
        functionBean1.title = "收款管理";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.fkgl;
        functionBean1.title = "付款管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.dzqr;
        functionBean1.title = "到账确认";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.yjkgl;
        functionBean1.title = "应缴款管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.yskcx;
        functionBean1.title = "应收款查询";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.yfkcx;
        functionBean1.title = "应付款查询";
        children.add(functionBean1);
        functionBean.children = children;


        functionBean = new FunctionBean();
        functionBean.icon = R.drawable.kcgl;
        functionBean.title = "库存管理";
        beanArrayList.add(functionBean);

        children = new ArrayList<>();
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.thgl;
        functionBean1.title = "提货管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.fhgl;
        functionBean1.title = "发货管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.tuihgl;
        functionBean1.title = "退货管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.ydkccx;
        functionBean1.title = "移动库存查询";
        children.add(functionBean1);
        functionBean.children = children;
        return beanArrayList;
    }

    public ArrayList<FunctionBean> getAllFunction() {
        if (allFunction==null){
            allFunction = getAllFunctionCopies();
        }
        return allFunction;
    }


}
