package com.jqyd.yuerduo.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jqyd.yuerduo.MyApplication;
import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.bean.FunctionBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 常用功能新增界面
 */
public class MainFunctionAddActivity extends BaseActivity {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.topBar_back)
    ImageButton topBarBack;
    @Bind(R.id.topBar_title)
    TextView topBarTitle;
    @Bind(R.id.topBar_right_button)
    TextView topbarRightButton;


    List<FunctionBean> allFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_function_add);
        ButterKnife.bind(this);
        topBarBack.setVisibility(View.VISIBLE);
        topbarRightButton.setVisibility(View.VISIBLE);
        topBarTitle.setText("常用功能");

        Intent intent = getIntent();
        ArrayList<FunctionBean> selectedList = (ArrayList<FunctionBean>) intent.getSerializableExtra("functionList");
        allFunctions = ((MyApplication)getApplication()).getAllFunctionCopies();
        allFunctions = getAllFunctions(allFunctions, selectedList);


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new MyAdapter(allFunctions));

        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(0x00000000)
                        .size(30)
                        .build());
    }


    @OnClick(R.id.topBar_back)
    public void onBack() {
        finish();
    }

    @OnClick(R.id.topBar_right_button)
    public void onOk() {
        Intent data = new Intent();
        ArrayList<FunctionBean> dataList = new ArrayList<>();
        if (allFunctions != null) {
            for (int i = 0; i < allFunctions.size(); i++) {
                FunctionBean functionBean = allFunctions.get(i);
                if (functionBean.checked && functionBean.level != 1) {
                    dataList.add(functionBean);
                }
            }
        }
        data.putExtra("dataList", dataList);
        setResult(Activity.RESULT_OK, data);
        finish();
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        List<FunctionBean> functionBeanList = new ArrayList<>();

        public MyAdapter(List<FunctionBean> functionBeanList) {
            this.functionBeanList = functionBeanList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            TextView textView = new TextView(parent.getContext());
            LayoutInflater layoutInflater = LayoutInflater.from(MainFunctionAddActivity.this);
            View view;
            if (viewType == 1) {
                view = layoutInflater.inflate(R.layout.layout_grid_title_function_add, parent, false);
            } else {
                view = layoutInflater.inflate(R.layout.layout_grid_item_function_add, parent, false);
            }
            StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            FunctionBean functionBean = functionBeanList.get(position);
            if (functionBean == null) {
                holder.itemView.setVisibility(View.GONE);
            } else {
                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
//                StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (functionBeanList.get(position).level == 1) {
                    layoutParams.setFullSpan(true);
                } else {
                    layoutParams.setFullSpan(false);
                }
                holder.itemView.setLayoutParams(layoutParams);
                holder.textView.setTag(functionBean);
                holder.itemView.setVisibility(View.VISIBLE);
                holder.textView.setText(functionBean.title);
                if (functionBean.level != 1) {
                    holder.textView.setChecked(functionBean.checked);
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            return functionBeanList.get(position).level;
        }

        @Override
        public int getItemCount() {
            return functionBeanList.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            CheckBox textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = (CheckBox) itemView.findViewById(R.id.tv_title);
                textView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        FunctionBean func = (FunctionBean) buttonView.getTag();
                        boolean preCheck = func.checked;
                        func.checked = isChecked;
                        if (func.level == 1 && func.children != null) {
                            func.checked = !preCheck;
                            int itemCount = func.children.size();
                            for (int i = 0; i < itemCount; i++) {
                                func.children.get(i).checked = func.checked;
                            }
                            if (itemCount > 0) {
                                int from = functionBeanList.indexOf(func) + 1;
                                notifyItemRangeChanged(from, itemCount);
                            }
                        }
                    }
                });
            }
        }
    }

    public List<FunctionBean> getAllFunctions(List<FunctionBean> functionBeanList, ArrayList<FunctionBean> selectedList) {
        List<FunctionBean> result = new ArrayList<>();
        for (FunctionBean function :
                functionBeanList) {
            result.add(function);
            if (function.children.size() != 0) {
                boolean allChecked = true;
                for (int i = function.children.size() - 1; i >= 0; i--) {
                    FunctionBean child = function.children.get(i);
                    if (child == null) {
                        function.children.remove(i);
                        continue;
                    }
                    child.level = 2;
                    if (selectedList == null) continue;
                    for (int j = 0; j < selectedList.size(); j++) {
                        FunctionBean selectedFunc = selectedList.get(j);
                        if (child.equals(selectedFunc)) {
                            child.checked = true;
                            break;
                        }
                    }
                    allChecked = allChecked && child.checked;
                }
                function.checked = allChecked;
                result.addAll(function.children);
            }
        }
        return result;
    }

}
