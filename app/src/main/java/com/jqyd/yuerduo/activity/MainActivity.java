package com.jqyd.yuerduo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.fragment.BaseFragment;
import com.jqyd.yuerduo.fragment.FunctionsFragment;
import com.jqyd.yuerduo.fragment.MainFragment;
import com.jqyd.yuerduo.fragment.MeFragment;
import com.jqyd.yuerduo.fragment.contacts.ContactsFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.nightfarmer.lightdialog.alert.AlertView;
import com.nightfarmer.lightdialog.common.listener.OnItemClickListener;
import com.nightfarmer.lightdialog.progress.ProgressHUD;
import com.nightfarmer.zxing.ScanHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.buttomBar)
    LinearLayout buttomBar;

    @Bind(R.id.topLayout)
    RelativeLayout topLayout;
    int topLayoutMaxHeight;
    int topLayoutMinHeight;

    @Bind(R.id.scanBt)
    ImageButton scanBt;

    @Bind(R.id.arrow_down)
    ImageButton arrow_down;

    @Bind(R.id.v_mask)
    View v_mask;

    LayoutInflater layoutInflater;

    private final List<ButtomBarItem> buttomBarItemList = new ArrayList<>();
    private final ArrayList<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UIUtils.setStatusBarTextColor(this, 1);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        topLayoutMaxHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
        topLayoutMinHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

        layoutInflater = LayoutInflater.from(this);

        fragmentList.add(new MainFragment());
        fragmentList.add(new ContactsFragment());
        fragmentList.add(new FunctionsFragment());
        fragmentList.add(new MeFragment());

        viewPager.setAdapter(new MainPageViewPagerAdapter());
        initButtomBar();
        viewPager.addOnPageChangeListener(new MainPageChangeListener());
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(0);
        animation.setFillAfter(true);
        v_mask.startAnimation(animation);
    }

    private int preSelected;

    @OnClick({R.id.tv_title, R.id.arrow_down})
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
        tv_title.getLocationOnScreen(location);
        int xpos = tv_title.getWidth() / 2 - popupWindow.getWidth() / 2;
        //xoff,yoff基于anchor的左下角进行偏移。
        popupWindow.showAsDropDown(tv_title, xpos, 0);

        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(600);
        animation.setFillAfter(true);
        v_mask.startAnimation(animation);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                AlphaAnimation animation = new AlphaAnimation(1, 0);
                animation.setDuration(600);
                animation.setFillAfter(true);
                v_mask.startAnimation(animation);
            }
        });
    }


    private void initButtomBar() {
        for (int i = 0; i < fragmentList.size(); i++) {
            BaseFragment fragment = fragmentList.get(i);
            View inflate = layoutInflater.inflate(R.layout.layout_buttom_item, buttomBar, false);
            ButtomBarItem buttomBarItem = new ButtomBarItem(i, inflate);
            buttomBar.addView(inflate);
            buttomBarItem.titleSelected.setText(fragment.getTitle());
            buttomBarItem.titleDefault.setText(fragment.getTitle());
            buttomBarItem.imageViewDefault.setImageResource(fragment.getIconDefault());
            buttomBarItem.imageViewSelected.setImageResource(fragment.getIconSelected());
            buttomBarItemList.add(buttomBarItem);
        }
        buttomBarItemList.get(0).redDot.setVisibility(View.VISIBLE);
    }

    private void setButtomBarItemSelected(ButtomBarItem buttomBarItem, float percent) {
        buttomBarItem.titleSelected.setAlpha(percent);
        buttomBarItem.titleDefault.setAlpha(1 - percent);
        buttomBarItem.imageViewSelected.setAlpha(percent);
        buttomBarItem.imageViewDefault.setAlpha(1 - percent);
    }

    public class ButtomBarItem {
        int index;
        View item;

        public ButtomBarItem(int index, View item) {
            this.index = index;
            this.item = item;
            ButterKnife.bind(this, item);
        }

        @Bind(R.id.imageViewDefault)
        ImageView imageViewDefault;
        @Bind(R.id.imageViewSelected)
        ImageView imageViewSelected;
        @Bind(R.id.itemTitleDefault)
        TextView titleDefault;
        @Bind(R.id.itemTitleSelected)
        TextView titleSelected;
        @Bind(R.id.red_dot)
        View redDot;

        @OnClick(R.id.buttomItem)
        public void onItemClick() {
            viewPager.setCurrentItem(index, false);
//            ViewGroup.LayoutParams layoutParams = topLayout.getLayoutParams();
//            if (index==fragmentList.size()-1){
//                layoutParams.height = topLayoutMaxHeight;
//                topLayout.setLayoutParams(layoutParams);
//            }else{
//                layoutParams.height = topLayoutMinHeight;
//                topLayout.setLayoutParams(layoutParams);
//            }
        }
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
                ButtomBarItem barItemCurrent = buttomBarItemList.get(currentItem);
                ButtomBarItem barItemNext = buttomBarItemList.get(nextItem);
                setButtomBarItemSelected(barItemCurrent, 1 - positionOffset);
                setButtomBarItemSelected(barItemNext, positionOffset);
//                ViewGroup.LayoutParams layoutParams = topLayout.getLayoutParams();
//                if (nextItem == buttomBarItemList.size() - 1) {
//                    layoutParams.height = (int) (topLayoutMinHeight + (topLayoutMaxHeight - topLayoutMinHeight) * positionOffset);
//                    topLayout.setLayoutParams(layoutParams);
//                }
            } else {//向左
                if (currentItem == 0) return;
                nextItem = currentItem - 1;
                ButtomBarItem barItemCurrent = buttomBarItemList.get(currentItem);
                ButtomBarItem barItemNext = buttomBarItemList.get(nextItem);
                setButtomBarItemSelected(barItemCurrent, positionOffset);
                setButtomBarItemSelected(barItemNext, 1 - positionOffset);
//                ViewGroup.LayoutParams layoutParams = topLayout.getLayoutParams();
//                if (nextItem == buttomBarItemList.size() - 2) {
//                    layoutParams.height = (int) (topLayoutMinHeight + (topLayoutMaxHeight - topLayoutMinHeight) * positionOffset);
//                    topLayout.setLayoutParams(layoutParams);
//                }
            }


        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < fragmentList.size(); i++) {
                if (i == position) continue;
                ButtomBarItem preItem = buttomBarItemList.get(i);
                setButtomBarItemSelected(preItem, 0);
                viewPager.setTag(position);
            }

            BaseFragment fragment = fragmentList.get(position);
            ButtomBarItem buttomBarItem = buttomBarItemList.get(position);
            setButtomBarItemSelected(buttomBarItem, 1);
            tv_title.setText(fragment.getTitle());

            if (position == fragmentList.size() - 1) {
                scanBt.setVisibility(View.VISIBLE);
            } else {
                scanBt.setVisibility(View.GONE);
            }
            if (position == 1) {
                arrow_down.setVisibility(View.VISIBLE);
            } else {
                arrow_down.setVisibility(View.GONE);
            }
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


    @OnClick(R.id.scanBt)
    public void onScan() {
        ScanHelper.capture(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String resultStr = ScanHelper.handlerData(requestCode, resultCode, data);
        if (resultStr != null) {
            Toast.makeText(MainActivity.this, "" + resultStr, Toast.LENGTH_SHORT).show();
        }
    }

    RequestHandle updateRequestHandle;
    AlertView alertView;
    AlertView alertView2;
    ProgressHUD mSVProgressHUD;

    @Override
    protected void onResume() {
        super.onResume();
        if (dialogIsShowing() || updateRequestHandle != null && !updateRequestHandle.isCancelled() && !updateRequestHandle.isFinished())
            return;

        alertView = new AlertView("提示", "发现新版本，是否更新？", "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int i) {
                if (-1 == i) {
                    alertView.dismiss();
                } else {
                    doDownLoad();
                }
            }
        });
        alertView.setCancelable(false);
        alertView.show();

    }

    private boolean dialogIsShowing() {
        return alertView != null && alertView.isShowing() || alertView2 != null && alertView2.isShowing()
                || mSVProgressHUD != null && mSVProgressHUD.isShowing();
    }

    private void doDownLoad() {
        mSVProgressHUD = new ProgressHUD(this);
        mSVProgressHUD.getProgressBar().setProgress(0);//先重设了进度再显示，避免下次再show会先显示上一次的进度位置所以要先将进度归0
        mSVProgressHUD.showWithProgress("更新进度 " + 0 + "%", ProgressHUD.SVProgressHUDMaskType.Black);

        alertView2 = new AlertView("提示", "更新失败，是否重试？", "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int i) {
                if (-1 == i) {
                    alertView2.dismiss();
                } else {
                    doDownLoad();
                }
            }
        });
        alertView2.setCancelable(false);

        AsyncHttpClient client = new AsyncHttpClient();
        boolean sdCardMounted = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
//        if (sdCardMounted) {
        File file = new File(Environment.getExternalStorageDirectory(), "JqEIP.apk");
//        }
        client.setMaxRetriesAndTimeout(10, 60000);
        updateRequestHandle = client.get("http://www.jqgj.com.cn/download/JqEIP.apk", new FileAsyncHttpResponseHandler(file) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Log.i("xx", "FF");
                Log.i("xx", "" + throwable.toString());
                mSVProgressHUD.dismiss();

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
