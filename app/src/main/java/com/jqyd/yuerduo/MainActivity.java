package com.jqyd.yuerduo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jqyd.yuerduo.fragment.BaseFragment;
import com.jqyd.yuerduo.fragment.ContactsFragment;
import com.jqyd.yuerduo.fragment.FunctionsFragment;
import com.jqyd.yuerduo.fragment.MainFragment;
import com.jqyd.yuerduo.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.tv_title)
    TextView textView;

    @Bind(R.id.buttomBar)
    LinearLayout buttomBar;

    @Bind(R.id.topLayout)
    RelativeLayout topLayout;
    int topLayoutMaxHeight;
    int topLayoutMinHeight;

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
            Object tag = viewPager.getTag();
            Integer index = 0;
            if (tag != null) {
                index = (Integer) tag;
            }
            ButtomBarItem preItem = buttomBarItemList.get(index);
            setButtomBarItemSelected(preItem, 0);
            viewPager.setTag(position);
            BaseFragment fragment = fragmentList.get(position);
            ButtomBarItem buttomBarItem = buttomBarItemList.get(position);
            setButtomBarItemSelected(buttomBarItem, 1);
            textView.setText(fragment.getTitle());
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (ViewPager.SCROLL_STATE_IDLE == state) {
                onPageSelected(viewPager.getCurrentItem());
            }
        }
    }


}
