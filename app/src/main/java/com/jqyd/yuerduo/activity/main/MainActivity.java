package com.jqyd.yuerduo.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.activity.BaseActivity;
import com.jqyd.yuerduo.fragment.BaseFragment;
import com.jqyd.yuerduo.fragment.FunctionsFragment;
import com.jqyd.yuerduo.fragment.MainFragment;
import com.jqyd.yuerduo.fragment.MeFragment;
import com.jqyd.yuerduo.fragment.contacts.ContactsFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.nightfarmer.lightdialog.alert.AlertView;
import com.nightfarmer.lightdialog.common.listener.OnDismissListener;
import com.nightfarmer.lightdialog.common.listener.OnItemClickListener;
import com.nightfarmer.lightdialog.progress.ProgressHUD;
import com.nightfarmer.zxing.ScanHelper;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends BaseActivity {

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.topBar_title)
    TextView topBar_title;

    @Bind(R.id.bottomBar)
    LinearLayout bottomBar;

    @Bind(R.id.topLayout)
    RelativeLayout topLayout;

    int topLayoutMaxHeight;
    int topLayoutMinHeight;


    @Bind(R.id.v_mask)
    View v_mask;

    LayoutInflater layoutInflater;

    Handler handler;

    private final List<BottomBarItem> bottomBarItemList = new ArrayList<>();
    private final ArrayList<BaseFragment> fragmentList = new ArrayList<>();

    private AlertView mAlertViewExt;
    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UIUtils.setStatusBarTextColor(this, 1);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        topBar = new TopBar(topLayout);


        handler = new Handler();
        topLayoutMaxHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
        topLayoutMinHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

        layoutInflater = LayoutInflater.from(this);

        fragmentList.add(new MainFragment());
        fragmentList.add(new ContactsFragment());
        fragmentList.add(new FunctionsFragment());
        fragmentList.add(new MeFragment());

        viewPager.setAdapter(new MainPageViewPagerAdapter());
        initBottomBar();
        fragmentList.get(0).doWithTopBar(topBar);
        viewPager.addOnPageChangeListener(new MainPageChangeListener());

    }

    private int preSelected;

    @OnClick({R.id.topBar_title, R.id.arrow_down})
    public void onTitleCheck() {
        if (viewPager.getCurrentItem() != 1) return;
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.GRAY);
        layout.setBackgroundResource(R.drawable.popover_background);
        final ListView tv = new ListView(this);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(tv);
        final List<String> listx = new ArrayList<>();
        listx.add("渠道通讯录");
        listx.add("员工通讯录");
        tv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return listx.size();
            }

            @Override
            public Object getItem(int position) {
                return listx.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_list_item_pop, parent, false);
                TextView tv_item = (TextView) inflate.findViewById(R.id.tv_item);
                tv_item.setText(listx.get(position));
                if (position == preSelected) {
                    tv_item.setTextColor(0xff38ADFF);
                } else {
                    tv_item.setTextColor(0xff737373);
                }
                return inflate;
            }
        });
//        tv.setAdapter(new SimpleAdapter(this, list, R.layout.layout_list_item_pop, from, to));
        float with = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        final PopupWindow popupWindow = new PopupWindow(layout, (int) with, (int) height);
        tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                TextView viewById = (TextView) view.findViewById(R.id.tv_item);
                viewById.setTextColor(0xff38ADFF);
                preSelected = position;
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        int[] location = new int[2];
        topBar_title.getLocationOnScreen(location);
        int xpos = topBar_title.getWidth() / 2 - popupWindow.getWidth() / 2;
        //xoff,yoff基于anchor的左下角进行偏移。
        popupWindow.showAsDropDown(topBar_title, xpos, 0);

        TransitionDrawable transition = (TransitionDrawable) v_mask.getBackground();
        transition.startTransition(600);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                TransitionDrawable transition = (TransitionDrawable) v_mask.getBackground();
                transition.reverseTransition(600);
            }
        });
    }


    private void initBottomBar() {
        for (int i = 0; i < fragmentList.size(); i++) {
            BaseFragment fragment = fragmentList.get(i);
            View inflate = layoutInflater.inflate(R.layout.layout_buttom_item, bottomBar, false);
            BottomBarItem bottomBarItem = new BottomBarItem(i, inflate, viewPager);
            bottomBar.addView(inflate);
            bottomBarItem.titleSelected.setText(fragment.getTitle());
            bottomBarItem.titleDefault.setText(fragment.getTitle());
            bottomBarItem.imageViewDefault.setImageResource(fragment.getIconDefault());
            bottomBarItem.imageViewSelected.setImageResource(fragment.getIconSelected());
            bottomBarItemList.add(bottomBarItem);
        }
        bottomBarItemList.get(0).redDot.setVisibility(View.VISIBLE);
    }

    private void setBottomBarItemSelected(BottomBarItem bottomBarItem, float percent) {
        bottomBarItem.titleSelected.setAlpha(percent);
        bottomBarItem.titleDefault.setAlpha(1 - percent);
        bottomBarItem.imageViewSelected.setAlpha(percent);
        bottomBarItem.imageViewDefault.setAlpha(1 - percent);
    }


    /**
     * viewpager适配器
     */
    private class MainPageViewPagerAdapter extends FragmentPagerAdapter {

        public MainPageViewPagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    /**
     * viewpager切换监听
     */
    private class MainPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int currentItem = viewPager.getCurrentItem();
            int nextItem;

            if (position == currentItem) {//向右
                if (currentItem == fragmentList.size() - 1) return;
                nextItem = currentItem + 1;
                BottomBarItem barItemCurrent = bottomBarItemList.get(currentItem);
                BottomBarItem barItemNext = bottomBarItemList.get(nextItem);
                setBottomBarItemSelected(barItemCurrent, 1 - positionOffset);
                setBottomBarItemSelected(barItemNext, positionOffset);
//                ViewGroup.LayoutParams layoutParams = topLayout.getLayoutParams();
//                if (nextItem == bottomBarItemList.size() - 1) {
//                    layoutParams.height = (int) (topLayoutMinHeight + (topLayoutMaxHeight - topLayoutMinHeight) * positionOffset);
//                    topLayout.setLayoutParams(layoutParams);
//                }
            } else {//向左
                if (currentItem == 0) return;
                nextItem = currentItem - 1;
                BottomBarItem barItemCurrent = bottomBarItemList.get(currentItem);
                BottomBarItem barItemNext = bottomBarItemList.get(nextItem);
                setBottomBarItemSelected(barItemCurrent, positionOffset);
                setBottomBarItemSelected(barItemNext, 1 - positionOffset);
//                ViewGroup.LayoutParams layoutParams = topLayout.getLayoutParams();
//                if (nextItem == bottomBarItemList.size() - 2) {
//                    layoutParams.height = (int) (topLayoutMinHeight + (topLayoutMaxHeight - topLayoutMinHeight) * positionOffset);
//                    topLayout.setLayoutParams(layoutParams);
//                }
            }


        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < fragmentList.size(); i++) {
                if (i == position) continue;
                BottomBarItem preItem = bottomBarItemList.get(i);
                setBottomBarItemSelected(preItem, 0);
                viewPager.setTag(position);
            }

            BaseFragment fragment = fragmentList.get(position);
            BottomBarItem bottomBarItem = bottomBarItemList.get(position);
            setBottomBarItemSelected(bottomBarItem, 1);
            fragment.doWithTopBar(topBar);

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (ViewPager.SCROLL_STATE_IDLE == state) {
                onPageSelected(viewPager.getCurrentItem());
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();
    }


    @OnClick(R.id.topBar_right_icon)
    public void onScan() {
        ScanHelper.capture(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        String resultStr = ScanHelper.handlerData(requestCode, resultCode, data);
//        if (resultStr != null) {
//            Toast.makeText(MainActivity.this, "" + resultStr, Toast.LENGTH_SHORT).show();
//        }

        String resultStr = ScanHelper.handlerData(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED) {
            Logger.i("二维码扫描结果：" + resultStr);
            if (resultStr != null) {
                mAlertViewExt = new AlertView("提示", "扫描结果" + resultStr, "确定", null, null, this, AlertView.Style.Alert, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int i) {
                        mAlertViewExt.dismiss();
                    }
                });
                mAlertViewExt.show();
            }
        }
    }

    RequestHandle updateRequestHandle;
    AlertView alertView;
    AlertView alertView2;
    ProgressHUD mSVProgressHUD;

    public boolean mastUpdate;

    @Override
    protected void onResume() {
        super.onResume();

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("versionName", "1.1.0");
        params.put("releaseTag", "debug");
        client.post(this, "http://120.27.107.170/yuerduo-seller/sellerApi/mobileupgradeapi/getupgrademessage", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Logger.json(response.toString());
                try {
                    int result = response.getInt("result");
                    if (result == 1) {
                        mastUpdate = response.getInt("ismust") != 0;
                        String url = response.getString("url");
                        doUpdate(url);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private void doUpdate(final String url) {
        if (dialogIsShowing() || updateRequestHandle != null && !updateRequestHandle.isCancelled() && !updateRequestHandle.isFinished())
            return;

        alertView = new AlertView("提示", "发现新版本，是否更新？", "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, final int i) {
                alertView.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(Object o) {
                        if (-1 == i) {
                            if (mastUpdate) finish();
                        } else {
                            doDownLoad(url);
                        }
                    }
                });
                alertView.dismiss();
            }
        });
        alertView.setCancelable(false);
        alertView.show();
    }

    private boolean dialogIsShowing() {
        return alertView != null && alertView.isShowing() || alertView2 != null && alertView2.isShowing()
                /*|| mSVProgressHUD != null && mSVProgressHUD.isShowing()*/;
    }

    private void doDownLoad(final String url) {
//        if (mSVProgressHUD == null) {
        mSVProgressHUD = new ProgressHUD(this);
//        }
        mSVProgressHUD.getProgressBar().setProgress(0);//先重设了进度再显示，避免下次再show会先显示上一次的进度位置所以要先将进度归0
        mSVProgressHUD.showWithProgress("更新进度 " + 0 + "%", ProgressHUD.SVProgressHUDMaskType.Black);
        if (alertView2 == null) {
            alertView2 = new AlertView("提示", "更新失败，是否重试？", "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, new OnItemClickListener() {
                @Override
                public void onItemClick(Object o, final int i) {
                    alertView2.setOnDismissListener(new OnDismissListener() {
                        @Override
                        public void onDismiss(Object o) {
                            if (-1 == i) {
                                if (mastUpdate) finish();
                            } else {
                                doDownLoad(url);
                            }
                        }
                    });
                    alertView2.dismiss();
                }
            });
            alertView2.setCancelable(false);
        }

        AsyncHttpClient client = new AsyncHttpClient();
        boolean sdCardMounted = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
//        if (sdCardMounted) {
        File file = new File(Environment.getExternalStorageDirectory(), "yuerduo.apk");
//        }
        client.setMaxRetriesAndTimeout(10, 30000);
        updateRequestHandle = client.get(url, new FileAsyncHttpResponseHandler(file) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Log.i("xx", "FF");
                Log.i("xx", "" + throwable.toString());
                mSVProgressHUD.dismissImmediately();
                alertView2.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                Log.i("xx", "SS");
                mSVProgressHUD.dismiss();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                MainActivity.this.startActivity(intent);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                if (mSVProgressHUD.getProgressBar().getMax() != mSVProgressHUD.getProgressBar().getProgress()) {
                    int progress = (int) (bytesWritten * 100.0 / totalSize);
                    mSVProgressHUD.getProgressBar().setProgress(progress);
                    mSVProgressHUD.setText("更新进度 " + progress + "%");
                } else {
                    mSVProgressHUD.dismiss();
                }
            }
        });
    }


}
