package com.playdate.MessageHomeModule;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.playdate.MainModule.MainActivity;
import com.playdate.ProfileModule.ViewProfileFragment;
import com.playdate.R;
import com.playdate.Utils.ProjectUtilities;
import com.playdate.Utils.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by Chetan on 01-01-2017.
 */

public class ConversationAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String user_id = "1";

    private ArrayList<ConversationPojo> conversationPojoArrayList;

    public ConversationAdapter(Context mContext, ArrayList<ConversationPojo> conversationPojoArrayList) {
        this.mContext = mContext;
        this.conversationPojoArrayList = conversationPojoArrayList;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return conversationPojoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return conversationPojoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_message_left, null);

            holder = new ViewHolder();

            holder.message_layout = (LinearLayout) convertView.findViewById(R.id.message_layout);
            holder.ivUserProfileIconLeft = (RoundedImageView) convertView.findViewById(R.id.ivUserProfileIconLeft);
            holder.ivUserProfileIconRight = (RoundedImageView) convertView.findViewById(R.id.ivUserProfileIconRight);
            holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            holder.tvDateAgo = (TextView) convertView.findViewById(R.id.tvDateAgo);
            holder.tvMessage = (TextView) convertView.findViewById(R.id.tvMessage);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (user_id.equals(conversationPojoArrayList.get(position).getUser_id())) {
            holder.ivUserProfileIconRight.setVisibility(View.VISIBLE);
            holder.ivUserProfileIconLeft.setVisibility(View.GONE);
            holder.message_layout.setBackgroundResource(R.drawable.chat_bubble);
        } else {
            holder.ivUserProfileIconLeft.setVisibility(View.VISIBLE);
            holder.ivUserProfileIconRight.setVisibility(View.GONE);
            holder.message_layout.setBackgroundResource(R.drawable.chat_bubble_gray);
        }

        holder.tvUserName.setText(conversationPojoArrayList.get(position).getFirst_name());
        holder.tvDateAgo.setText(ProjectUtilities.getTimeAgo(ProjectUtilities.getTimeMili(conversationPojoArrayList.get(position).getDate())));
        holder.tvMessage.setText(conversationPojoArrayList.get(position).getMessage());

        holder.ivUserProfileIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.replaceFragmentWithBackStack(mContext, new ViewProfileFragment());
            }
        });


        return convertView;
    }

    class ViewHolder {
        RoundedImageView ivUserProfileIconLeft, ivUserProfileIconRight;
        TextView tvUserName, tvDateAgo, tvMessage;
        LinearLayout message_layout;
    }
}
