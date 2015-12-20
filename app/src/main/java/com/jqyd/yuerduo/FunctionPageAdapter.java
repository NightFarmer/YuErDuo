package com.jqyd.yuerduo;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jqyd.yuerduo.activity.FunctionsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangfan on 2015/12/18.
 */
public class FunctionPageAdapter extends RecyclerView.Adapter<FunctionPageAdapter.MyViewHolder> {

    public List<FunctionBean> dataList = new ArrayList<>();


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item_function, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FunctionBean functionBean = dataList.get(position);
        holder.title.setText(functionBean.title);
        holder.imageView.setImageResource(functionBean.icon);
        holder.itemView.setTag(functionBean);
        if (0==position){
            holder.red_dot.setVisibility(View.VISIBLE);
        }else {
            holder.red_dot.setVisibility(View.GONE);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.functionImage)
        ImageView imageView;
        @Bind(R.id.tv_title)
        TextView title;
        @Bind(R.id.red_dot)
        View red_dot;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.list_item)
        public void onClick(View view){
            FunctionBean tag = (FunctionBean) itemView.getTag();
            Context context = view.getContext();
            Intent intent = new Intent(context, FunctionsActivity.class);
            intent.putExtra("function", tag);
            context.startActivity(intent);
        }
    }
}
