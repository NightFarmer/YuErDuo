package com.jqyd.yuerduo.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.activity.PersonDetail;
import com.jqyd.yuerduo.activity.main.TopBar;
import com.nightfarmer.lightdialog.alert.AlertView;
import com.nightfarmer.lightdialog.common.listener.OnItemClickListener;
import com.nightfarmer.zxing.ScanHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangfan on 2015/12/14.
 */
public class MeFragment extends BaseFragment {

    @Bind(R.id.userImage)
    ImageView userImage;


    private AlertView mAlertViewExt;

    @Override
    public String getTitle() {
        return "我";
    }

    @Override
    public int getIconDefault() {
        return R.drawable.more0;
    }

    @Override
    public int getIconSelected() {
        return R.drawable.more1;
    }

    @Override
    public void doWithTopBar(TopBar topBar) {
        super.doWithTopBar(topBar);
        topBar.right_icon.setVisibility(View.VISIBLE);
        topBar.arrow_down.setVisibility(View.GONE);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, inflate);



        return inflate;
    }

    @OnClick(R.id.iv_showQR)
    public void showQR(){
        mAlertViewExt = new AlertView(null, null, "确定", null, null, getContext(), AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int i) {
                mAlertViewExt.dismiss();
            }
        });
        ImageView extView = new ImageView(getContext());
        extView.setImageBitmap(ScanHelper.encodingMap("王洪斌", 500));
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.show();
    }

    @OnClick({R.id.userImage, R.id.userInfo})
    public void goUserInfo(){
        startActivity(new Intent(getActivity(), PersonDetail.class));
    }


}
