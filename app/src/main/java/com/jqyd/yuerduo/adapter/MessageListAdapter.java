package com.jqyd.yuerduo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.bean.MessageBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangfan on 2015/12/21.
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MyViewHolder> {

    public List<MessageBean> dataList = new ArrayList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_message_list_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText("这是标题这是标题这是标题");
        holder.tv_content.setText("这是很长很长的内容这是很长很长的内容这是很长很长的内容这是很长很长的内容这是很长很长的内容这是很长很长的内容这是很长很长的内容这是很长很长的内容123");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_content)
        TextView tv_content;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
