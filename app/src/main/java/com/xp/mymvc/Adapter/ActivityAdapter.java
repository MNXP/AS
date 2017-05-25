package com.xp.mymvc.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xp.mymvc.Bean.ActivityListBean;
import com.xp.mymvc.R;
import com.xp.mymvc.Tool.Util.AppUtil;

import java.util.List;

/**
 * 描  述：活动中心Adapter
 * 开发者：x i a n g  p a n
 * 时  间：2017/5/18.
 */

public class ActivityAdapter extends BaseAdapter {
    private List<ActivityListBean.ActivityBean> list;
    private Context context;

    public ActivityAdapter(List<ActivityListBean.ActivityBean> list,Context context){
        this.list = list;
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context,
                    R.layout.list_item_hdzx, null);
            holder = new ViewHolder();
            holder.descrition = (TextView) convertView.findViewById(R.id.tv_date);
            holder.title = (TextView) convertView.findViewById(R.id.tv_name);
            holder.overTv = (TextView) convertView.findViewById(R.id.activity_over);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            int screenWidth = AppUtil.getScreenDispaly(context)[0]; // 获取屏幕宽度
            ViewGroup.LayoutParams lp = holder.iv.getLayoutParams();
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = screenWidth/2;
            holder.iv.setLayoutParams(lp);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ActivityListBean.ActivityBean tList = getItem(position);
        String flag = tList.getStatusFlag();
        if (TextUtils.equals("3",flag)){
            holder.overTv.setVisibility(View.VISIBLE);
        }else {
            holder.overTv.setVisibility(View.GONE);
        }
        holder.title.setText(tList.getTitle());
        Picasso.with(context).load(tList.getUrl()).placeholder(R.mipmap.activity_content_no_data).error(R.mipmap.activity_content_no_data).into(holder.iv);
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ActivityListBean.ActivityBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }



    static class ViewHolder {
        // 描述
        public TextView descrition;
        // 名字
        public TextView title;
        // 图片
        public ImageView iv;
        //已结束标签
        public TextView overTv;

    }
}


