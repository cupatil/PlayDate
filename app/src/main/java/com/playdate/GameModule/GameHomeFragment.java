package com.playdate.GameModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.playdate.R;

import java.util.ArrayList;

/**
 * Created by Chetan on 16-01-2017.
 */

public class GameHomeFragment extends Fragment {

    private ListView lvGame;
    private View rootView;

    private ArrayList<GamePojo> gamePojoArrayList = new ArrayList<>();
    private GameHomeAdapter gameHomeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_game_home, null);

        setDataList();

        initView();

        return rootView;
    }


    private void initView() {
        lvGame = (ListView) rootView.findViewById(R.id.lvGame);

        gameHomeAdapter=new GameHomeAdapter(getActivity(),gamePojoArrayList);
        lvGame.setAdapter(gameHomeAdapter);
    }

    private void setDataList() {

        //user_id, user_name, remaining_time, percentage, streak, game_status;

        gamePojoArrayList.clear();
        gamePojoArrayList.add(new GamePojo("1","Amanda Lane", "23 Hours Left!","20","43","1"));
        gamePojoArrayList.add(new GamePojo("2","Gillion Woong", "23 Hours Left!","20","9","1"));
        gamePojoArrayList.add(new GamePojo("3","Jessica Hortono", "23 Hours Left!","20","14","2"));
        gamePojoArrayList.add(new GamePojo("4","Amanda Lane", "23 Hours Left!","20","43","2"));
        gamePojoArrayList.add(new GamePojo("5","Kelly Andreson", "23 Hours Left!","20","43","3"));



    }


}
