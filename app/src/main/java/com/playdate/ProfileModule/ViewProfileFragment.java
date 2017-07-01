package com.playdate.ProfileModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.playdate.HomeModule.UserPojo;
import com.playdate.MainModule.MainActivity;
import com.playdate.R;
import com.playdate.Utils.ProjectUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chetan on 16-01-2017.
 */

public class ViewProfileFragment extends Fragment {


    private RecyclerView image_recycler_view, instgram_recycler_view;
    private View rootView;
    private List<UserPojo> dataList = new ArrayList<>();
    private ImageHorizontalAdapter imageHorizontalAdapter, instgramImageHorizontalAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_profile, null);

        MainActivity.hideShowToolbarIcon("back");

        setImageList();

        initView();

        setHasOptionsMenu(true);
        return rootView;
    }

    private void initView() {
        image_recycler_view = (RecyclerView) rootView.findViewById(R.id.image_recycler_view);
        instgram_recycler_view = (RecyclerView) rootView.findViewById(R.id.instgram_recycler_view);

        imageHorizontalAdapter = new ImageHorizontalAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        image_recycler_view.setLayoutManager(mLayoutManager);
        image_recycler_view.setItemAnimator(new DefaultItemAnimator());
        image_recycler_view.setAdapter(imageHorizontalAdapter);

        instgramImageHorizontalAdapter = new ImageHorizontalAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        instgram_recycler_view.setLayoutManager(mLayoutManager1);
        instgram_recycler_view.setItemAnimator(new DefaultItemAnimator());
        instgram_recycler_view.setAdapter(instgramImageHorizontalAdapter);
    }

    private void setImageList() {
        dataList.clear();
        for (int i = 0; i < ProjectUtilities.imagePaths.length; i++) {
            UserPojo dataItem = new UserPojo();
            dataItem.setId("1");
            dataItem.setFirst_name(ProjectUtilities.names[i]);
            dataItem.setProfile_pic(ProjectUtilities.imagePaths[i]);
            //dataItem.setAge("23");
            dataList.add(dataItem);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_view_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case R.id.menu_messages:
                break;
            case R.id.menu_game:
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
