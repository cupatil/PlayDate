package com.playdate.GameModule;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.playdate.HomeModule.UserPojo;
import com.playdate.R;
import com.playdate.Utils.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by Chetan on 01-01-2017.
 */

public class GameStartAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<UserPojo> userPojoArrayList;

    public GameStartAdapter(Context mContext, ArrayList<UserPojo> userPojoArrayList) {
        this.mContext = mContext;
        this.userPojoArrayList = userPojoArrayList;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return userPojoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userPojoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_start_game, null);

            holder = new ViewHolder();


            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);
            holder.ivUserProfileIcon = (RoundedImageView) convertView.findViewById(R.id.ivUserProfileIcon);
            holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            holder.tvStart = (TextView) convertView.findViewById(R.id.tvStart);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivUserProfileIcon.setBorderColor(Color.parseColor("#FE0036"));
        holder.ivUserProfileIcon.setBorderWidth((float) 2);

        holder.tvUserName.setText(userPojoArrayList.get(position).getFirst_name());

        holder.tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    class ViewHolder {
        RelativeLayout relativeLayout;
        RoundedImageView ivUserProfileIcon;
        TextView tvUserName, tvStart;
    }
}
