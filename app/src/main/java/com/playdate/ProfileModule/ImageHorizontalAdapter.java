package com.playdate.ProfileModule;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.playdate.HomeModule.UserPojo;
import com.playdate.R;

import java.util.List;

public class ImageHorizontalAdapter extends RecyclerView.Adapter<ImageHorizontalAdapter.MyViewHolder> {

    private List<UserPojo> userPojoList;

    public ImageHorizontalAdapter(List<UserPojo> userPojoList) {
        this.userPojoList = userPojoList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivUserProfileIcon;

        public MyViewHolder(View view) {
            super(view);

            ivUserProfileIcon = (ImageView) view.findViewById(R.id.ivUserProfileIcon);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_recycleview_image_profile, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserPojo userPojo = userPojoList.get(position);

        ImageLoader.getInstance().displayImage(userPojo.getProfile_pic(), holder.ivUserProfileIcon);


    }

    @Override
    public int getItemCount() {
        return userPojoList.size();
    }
}