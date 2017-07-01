package com.playdate.CategoryModule;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.playdate.R;

import java.util.ArrayList;

/**
 * Created by Chetan on 01-01-2017.
 */

public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private ArrayList<CategoryPojo> categoryPojoArrayList;

    public CategoryAdapter(Context mContext, ArrayList<CategoryPojo> categoryPojoArrayList) {
        this.mContext = mContext;
        this.categoryPojoArrayList = categoryPojoArrayList;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return categoryPojoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryPojoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_category, null);

            holder = new ViewHolder();

            holder.tvCategoryName = (TextView) convertView.findViewById(R.id.tvCategoryName);
            holder.ivStar = (ImageView) convertView.findViewById(R.id.ivStar);
            holder.recyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvCategoryName.setText(categoryPojoArrayList.get(position).getCategory_name());


        if (categoryPojoArrayList.get(position).isFavourite()) {
            holder.ivStar.setVisibility(View.VISIBLE);
        } else {
            holder.ivStar.setVisibility(View.GONE);
        }

        holder.tvCategoryName.setTextColor(Color.parseColor(categoryPojoArrayList.get(position).getColor()));
        holder.linearLayout.setBackgroundColor(Color.parseColor(categoryPojoArrayList.get(position).getColor()));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(mLayoutManager);
        holder.recyclerView.setItemAnimator(new DefaultItemAnimator());

        CategoryPersonHorizontalAdapter categoryPersonHorizontalAdapter = new CategoryPersonHorizontalAdapter(
                mContext, categoryPojoArrayList.get(position).getUserPojoArrayList());
        holder.recyclerView.setAdapter(categoryPersonHorizontalAdapter);


        return convertView;
    }

    class ViewHolder {
        TextView tvCategoryName;
        ImageView ivStar;
        RecyclerView recyclerView;
        LinearLayout linearLayout;
    }
}
