package com.playdate.MessageHomeModule;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.playdate.R;
import com.playdate.Utils.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by Chetan on 01-01-2017.
 */

public class UserMessageListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<UserMessageHomePojo> userMessageHomePojoArrayList;

    public UserMessageListAdapter(Context mContext, ArrayList<UserMessageHomePojo> userMessageHomePojoArrayList) {
        this.mContext = mContext;
        this.userMessageHomePojoArrayList = userMessageHomePojoArrayList;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return userMessageHomePojoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userMessageHomePojoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_user_message, null);

            holder = new ViewHolder();


            holder.ivUserProfileIcon = (RoundedImageView) convertView.findViewById(R.id.ivUserProfileIcon);
            holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            holder.tvMessage = (TextView) convertView.findViewById(R.id.tvMessage);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivUserProfileIcon.setBorderColor(Color.parseColor("#FE0036"));
        holder.ivUserProfileIcon.setBorderWidth((float) 2);

        holder.tvUserName.setText(userMessageHomePojoArrayList.get(position).getFirst_name());
        holder.tvMessage.setText(userMessageHomePojoArrayList.get(position).getLast_message());
        return convertView;
    }

    class ViewHolder {
        RoundedImageView ivUserProfileIcon;
        TextView tvUserName, tvMessage;
    }
}
