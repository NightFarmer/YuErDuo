package com.jqyd.yuerduo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jqyd.yuerduo.R;
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
}
