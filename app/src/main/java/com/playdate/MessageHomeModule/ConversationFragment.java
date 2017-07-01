package com.playdate.MessageHomeModule;

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
import android.widget.ImageView;
import android.widget.ListView;

import com.playdate.HomeModule.UserPojo;
import com.playdate.MainModule.MainActivity;
import com.playdate.R;
import com.playdate.Utils.ProjectUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chetan on 16-01-2017.
 */

public class ConversationFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recycler_view;
    private ListView lvMessageConversation;
    private EditText etMessage;
    private ImageView btnSend;
    private View rootView;

    private ConversationAdapter conversationAdapter;
    private PersonHorizontalAdapter personHorizontalAdapter;
    private ArrayList<ConversationPojo> conversationPojoArrayList = new ArrayList<>();
    private List<UserPojo> dataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_message_chat, null);


        MainActivity.hideShowToolbarIcon("conversion");

        setConversationList();

        //Initialize view
        initView();

        setHasOptionsMenu(true);
        return rootView;
    }

    private void initView() {
        recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        lvMessageConversation = (ListView) rootView.findViewById(R.id.lvMessageConversation);
        etMessage = (EditText) rootView.findViewById(R.id.etMessage);
        btnSend = (ImageView) rootView.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(this);

        conversationAdapter = new ConversationAdapter(getActivity(), conversationPojoArrayList);
        lvMessageConversation.setAdapter(conversationAdapter);


        personHorizontalAdapter = new PersonHorizontalAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(personHorizontalAdapter);
    }

    private void setConversationList() {
        conversationPojoArrayList = new ArrayList<>();
        conversationPojoArrayList.add(new ConversationPojo("2", "Neha", "Shastri", "2017-01-16 12:12:12", "Hi..."));
        conversationPojoArrayList.add(new ConversationPojo("2", "Neha", "Shastri", "2017-01-16 12:12:12", "How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?How are you?"));
        conversationPojoArrayList.add(new ConversationPojo("1", "Dipak", "Tendulkar", "2017-01-16 12:12:12", "Hello."));
        conversationPojoArrayList.add(new ConversationPojo("1", "Dipak", "Tendulkar", "2017-01-16 12:12:12", "I'm fine. What about you?"));
        conversationPojoArrayList.add(new ConversationPojo("2", "Neha", "Shastri", "2017-01-16 12:12:12", "I'm also fine."));

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSend:
                break;
            default:
                break;
        }

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_conversation, menu);
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
