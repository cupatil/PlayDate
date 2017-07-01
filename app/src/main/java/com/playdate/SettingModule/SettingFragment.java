package com.playdate.SettingModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.playdate.MainModule.MainActivity;
import com.playdate.R;

/**
 * Created by Chetan on 16-01-2017.
 */

public class SettingFragment extends Fragment implements View.OnClickListener {

    private TextView tvGender, tvMen, tvWomen, tvMenWomen;

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_setting, null);

        MainActivity.hideShowToolbarIcon("back");

        initView();

        return rootView;
    }

    private void initView() {

        tvGender = (TextView) rootView.findViewById(R.id.tvGender);
        tvMen = (TextView) rootView.findViewById(R.id.tvMen);
        tvWomen = (TextView) rootView.findViewById(R.id.tvWomen);
        tvMenWomen = (TextView) rootView.findViewById(R.id.tvMenWomen);

        tvMen.setOnClickListener(this);
        tvWomen.setOnClickListener(this);
        tvMenWomen.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvMen:
                tvGender.setText(tvMen.getText());
                break;
            case R.id.tvWomen:
                tvGender.setText(tvWomen.getText());
                break;
            case R.id.tvMenWomen:
                tvGender.setText(tvMenWomen.getText());
                break;
            default:
                break;
        }
    }
}
