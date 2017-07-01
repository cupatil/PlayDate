package com.playdate.GameModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.playdate.HomeModule.UserPojo;
import com.playdate.MainModule.MainActivity;
import com.playdate.R;
import com.playdate.Utils.ProjectUtilities;

import java.util.ArrayList;

/**
 * Created by Chetan on 22-01-2017.
 */

public class GameStartFragment extends Fragment {

    private ListView lvGameStart;

    private View rootView;

    private GameStartAdapter gameStartAdapter;
    private ArrayList<UserPojo> userPojoArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_game_start, null);

        MainActivity.hideShowToolbarIcon("back");

        setDataList();

        initView();

        return rootView;
    }

    private void initView() {
        lvGameStart = (ListView) rootView.findViewById(R.id.lvGameStart);

        gameStartAdapter = new GameStartAdapter(getActivity(), userPojoArrayList);
        lvGameStart.setAdapter(gameStartAdapter);
    }

    private void setDataList() {

        userPojoArrayList.clear();

        for (int i = 3; i < 6; i++) {
            UserPojo dataItem = new UserPojo();
            dataItem.setId("1");
            dataItem.setFirst_name(ProjectUtilities.names[i]);
            dataItem.setProfile_pic(ProjectUtilities.imagePaths[i]);
            //dataItem.setAge("23");
            userPojoArrayList.add(dataItem);

        }

    }

}
