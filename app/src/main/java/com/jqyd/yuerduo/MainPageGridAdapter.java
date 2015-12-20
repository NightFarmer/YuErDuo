package com.jqyd.yuerduo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangfan on 2015/12/18.
 */
public class MainPageGridAdapter extends RecyclerView.Adapter<MainPageGridAdapter.MyViewHolder> {

    public List<FunctionBean> dataList = new ArrayList<>();


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item_function, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate, viewType);

        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return dataList.size() + 1;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            FunctionBean functionBean = dataList.get(position);
            holder.title.setText(functionBean.title);
            holder.imageView.setImageResource(functionBean.icon);
            holder.item_function.setTag(functionBean);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == dataList.size() ? -1 : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        int viewType;

        @Bind(R.id.functionImage)
        ImageView imageView;

        @Bind(R.id.tv_title)
        TextView title;

        @Bind(R.id.item_function)
        View item_function;

        @Bind(R.id.item_add)
        View item_add;

        @OnClick(R.id.grid_item)
        public void onClick(View v){
            if (0==viewType){
                FunctionBean functionBean = (FunctionBean) item_function.getTag();
                Toast.makeText(v.getContext(), functionBean.title, Toast.LENGTH_SHORT).show();
                return;
            }
            v.getContext().startActivity(new Intent(v.getContext(), MainFunctionAddActivity.class));
        }

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            ButterKnife.bind(this, itemView);
            if (0 == viewType) {
                item_function.setVisibility(View.VISIBLE);
                item_add.setVisibility(View.GONE);
            }else{
                item_function.setVisibility(View.GONE);
                item_add.setVisibility(View.VISIBLE);
            }
        }
    }

}
