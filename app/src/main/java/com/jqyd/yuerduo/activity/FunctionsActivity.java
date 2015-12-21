package com.jqyd.yuerduo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.bean.FunctionBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangfan on 2015/12/21.
 */
public class FunctionsActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);
        ButterKnife.bind(this);

        FunctionBean parent = (FunctionBean) getIntent().getSerializableExtra("function");
        if (parent != null) tv_title.setText(parent.title);

        layoutInflater = LayoutInflater.from(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FunctionListAdapter(parent.children));
    }


    public class FunctionListAdapter extends RecyclerView.Adapter<FunctionListAdapter.MyViewHolder> {
        List<FunctionBean> functionBeanList;

        public FunctionListAdapter(List<FunctionBean> functionBeanList) {
            this.functionBeanList = functionBeanList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = layoutInflater.inflate(R.layout.layout_list_item_function_small, parent, false);
            return new MyViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            FunctionBean functionBean = functionBeanList.get(position);
            if (null != functionBean) {
                holder.imageView.setImageResource(functionBean.icon);
                holder.title.setText(functionBean.title);

                FunctionBean pre = null;
                FunctionBean next = null;
                if (position > 0) pre = functionBeanList.get(position - 1);
                if (position < functionBeanList.size() - 1)
                    next = functionBeanList.get(position + 1);

                if (pre == null) {
                    holder.line_top.setVisibility(View.VISIBLE);
                }else{
                    holder.line_top.setVisibility(View.GONE);
                }
                if (next!=null){
                    holder.line_middle.setVisibility(View.VISIBLE);
                    holder.line_buttom.setVisibility(View.GONE);
                }else {
                    holder.line_middle.setVisibility(View.GONE);
                    holder.line_buttom.setVisibility(View.VISIBLE);
                }
                if (0!=position) holder.red_dot.setVisibility(View.GONE);
            } else {
                ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
                layoutParams.height = 20;
                holder.itemView.setLayoutParams(layoutParams);
                holder.itemView.setVisibility(View.INVISIBLE);
            }

        }


        @Override
        public int getItemCount() {
            return functionBeanList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.functionImage)
            ImageView imageView;
            @Bind(R.id.tv_title)
            TextView title;
            @Bind(R.id.red_dot)
            View red_dot;
            @Bind(R.id.line_top)
            View line_top;
            @Bind(R.id.line_middle)
            View line_middle;
            @Bind(R.id.line_buttom)
            View line_buttom;

            public MyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            @OnClick(R.id.list_item)
            public void onClick(View view) {
                FunctionBean tag = (FunctionBean) itemView.getTag();
                Context context = view.getContext();
                Intent intent = new Intent(context, MessageListActivity.class);
                intent.putExtra("function", tag);
                context.startActivity(intent);
            }
        }
    }
}
