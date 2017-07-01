package com.playdate.MessageHomeModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.playdate.MainModule.MainActivity;
import com.playdate.R;

import java.util.ArrayList;

/**
 * Created by Chetan on 01-01-2017.
 */

public class UserMessageListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private EditText etSearch;
    private ListView lvUserMessage;
    private View rootView;

    private UserMessageListAdapter userMessageListAdapter;
    private ArrayList<UserMessageHomePojo> userMessageHomePojoArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_message_home, null);

        MainActivity.hideShowToolbarIcon("message_fragment");

        setDemoListData();

        initView();

        setHasOptionsMenu(true);
        return rootView;
    }

    private void initView() {
        etSearch = (EditText) rootView.findViewById(R.id.etSearch);
        lvUserMessage = (ListView) rootView.findViewById(R.id.lvUserMessage);
        lvUserMessage.setOnItemClickListener(this);

        userMessageListAdapter = new UserMessageListAdapter(getActivity(), userMessageHomePojoArrayList);
        lvUserMessage.setAdapter(userMessageListAdapter);

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

    private void setDemoListData() {
        userMessageHomePojoArrayList = new ArrayList<>();
        userMessageHomePojoArrayList.add(new UserMessageHomePojo("Neha", "Hello, How are you?"));
        userMessageHomePojoArrayList.add(new UserMessageHomePojo("Pooja", "Hi!"));
        userMessageHomePojoArrayList.add(new UserMessageHomePojo("Shweta", "Hello, Are you there?"));
        userMessageHomePojoArrayList.add(new UserMessageHomePojo("Tejal", "Hi, Ravi"));
        userMessageHomePojoArrayList.add(new UserMessageHomePojo("Chitra", "Hello..."));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MainActivity.linearLayoutBottom.setVisibility(View.GONE);
        MainActivity.replaceFragmentWithBackStack(getActivity(), new ConversationFragment());
    }
}
