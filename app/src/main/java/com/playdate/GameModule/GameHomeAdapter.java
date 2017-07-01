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

import com.playdate.R;
import com.playdate.Utils.DonutProgress;
import com.playdate.Utils.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by Chetan on 01-01-2017.
 */

public class GameHomeAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<GamePojo> gamePojoArrayList;

    public GameHomeAdapter(Context mContext, ArrayList<GamePojo> gamePojoArrayList) {
        this.mContext = mContext;
        this.gamePojoArrayList = gamePojoArrayList;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return gamePojoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gamePojoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_game_home, null);

            holder = new ViewHolder();


            holder.donut_progress = (DonutProgress) convertView.findViewById(R.id.donut_progress);
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);
            holder.ivUserProfileIcon = (RoundedImageView) convertView.findViewById(R.id.ivUserProfileIcon);
            holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            holder.tvStreak = (TextView) convertView.findViewById(R.id.tvStreak);
            holder.tvTimeLeft = (TextView) convertView.findViewById(R.id.tvTimeLeft);
            holder.tvGo = (TextView) convertView.findViewById(R.id.tvGo);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.donut_progress.setDonut_progress("20");

        holder.tvStreak.setText(gamePojoArrayList.get(position).getStreak());
        holder.tvTimeLeft.setText(gamePojoArrayList.get(position).getRemaining_time());
        holder.tvUserName.setText(gamePojoArrayList.get(position).getUser_name());

        if (gamePojoArrayList.get(position).getGame_status().equals("1")) {
            holder.tvGo.setText("GO!");
            holder.tvGo.setTextSize(20);
            holder.tvGo.setTextColor(mContext.getResources().getColor(R.color.categorySky));
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#C9EDEB"));
        } else if (gamePojoArrayList.get(position).getGame_status().equals("2")) {
            holder.tvGo.setText("DRAW");
            holder.tvGo.setTextSize(20);
            holder.tvGo.setTextColor(mContext.getResources().getColor(R.color.categorySky));
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#C9EDEB"));
        } else {
            holder.tvGo.setText("Waiting for their Turn...");
            holder.tvGo.setTextSize(16);
            holder.tvGo.setTextColor(mContext.getResources().getColor(android.R.color.black));
            holder.relativeLayout.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        }


        holder.tvGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    class ViewHolder {
        RelativeLayout relativeLayout;
        RoundedImageView ivUserProfileIcon;
        TextView tvUserName, tvStreak, tvTimeLeft, tvGo;
        DonutProgress donut_progress;

    }
}
