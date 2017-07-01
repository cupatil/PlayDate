package com.playdate.CategoryModule;

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
import android.widget.EditText;
import android.widget.ListView;

import com.playdate.HomeModule.UserPojo;
import com.playdate.MainModule.MainActivity;
import com.playdate.R;
import com.playdate.Utils.ProjectUtilities;

import java.util.ArrayList;

/**
 * Created by Chetan on 16-01-2017.
 */

public class CategoryFragment extends Fragment {

    private EditText etSearch;
    private RecyclerView recycler_view_new_match;
    private ListView lvCategory;

    private View rootView;

    private ArrayList<CategoryPojo> categoryPojoArrayList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private ArrayList<UserPojo> dataList = new ArrayList<>();
    private CategoryPersonHorizontalAdapter categoryPersonHorizontalAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_category, null);

        MainActivity.hideShowToolbarIcon("game_home");

        setDataList();

        initView();

        setHasOptionsMenu(true);

        return rootView;
    }

    private void initView() {
        etSearch = (EditText) rootView.findViewById(R.id.etSearch);
        lvCategory = (ListView) rootView.findViewById(R.id.lvCategory);
        recycler_view_new_match = (RecyclerView) rootView.findViewById(R.id.recycler_view_new_match);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycler_view_new_match.setLayoutManager(mLayoutManager);
        recycler_view_new_match.setItemAnimator(new DefaultItemAnimator());

        categoryPersonHorizontalAdapter = new CategoryPersonHorizontalAdapter(getActivity(), dataList);
        recycler_view_new_match.setAdapter(categoryPersonHorizontalAdapter);


        categoryAdapter = new CategoryAdapter(getActivity(), categoryPojoArrayList);
        lvCategory.setAdapter(categoryAdapter);

    }

    private void setDataList() {
        dataList.clear();
        for (int i = 0; i < ProjectUtilities.imagePaths.length; i++) {
            UserPojo dataItem = new UserPojo();
            dataItem.setId("1");
            dataItem.setFirst_name(ProjectUtilities.names[i]);
            dataItem.setProfile_pic(ProjectUtilities.imagePaths[i]);
            //dataItem.setAge("23");
            dataList.add(dataItem);
        }

        categoryPojoArrayList.clear();
        categoryPojoArrayList.add(new CategoryPojo("1", "Hot Girls", true, "#ED165C", dataList));
        categoryPojoArrayList.add(new CategoryPojo("2", "Maybe", false, "#50BF86", dataList));
        categoryPojoArrayList.add(new CategoryPojo("3", "Nope", false, "#1EBCB4", dataList));
        // categoryPojoArrayList.add(new CategoryPojo("3", "Nope", false, "#FF9603", dataList));


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_message_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case R.id.menu_add:


                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
