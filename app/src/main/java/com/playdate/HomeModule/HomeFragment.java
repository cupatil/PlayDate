package com.playdate.HomeModule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.playdate.MainModule.MainActivity;
import com.playdate.R;
import com.playdate.Utils.AppConstant;
import com.playdate.Utils.ProjectUtilities;
import com.playdate.Utils.WebserviceUrl;
import com.playdate.placeholderview.SwipeDecor;
import com.playdate.placeholderview.SwipePlaceHolderView;
import com.playdate.placeholderview.listeners.ItemRemovedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private SwipePlaceHolderView mSwipView;
    private Button btnReject, btnRefresh, btnAccept;


    private List<UserPojo> dataList = new ArrayList<UserPojo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, null);


        MainActivity.hideShowToolbarIcon("home");

        initView(rootView);

        return rootView;
    }

    private void initView(View rootView) {

        mSwipView = (SwipePlaceHolderView) rootView.findViewById(R.id.swipeView);
        btnReject = (Button) rootView.findViewById(R.id.btnReject);
        btnRefresh = (Button) rootView.findViewById(R.id.btnRefresh);
        btnAccept = (Button) rootView.findViewById(R.id.btnAccept);


        mSwipView.addItemRemoveListener(new ItemRemovedListener() {

            @Override
            public void onItemRemoved(int count) {
                if (count == 0) {
                    mSwipView.addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard());
                }
            }
        });
        mSwipView.getBuilder()
//                .setSwipeType(SwipePlaceHolderView.SWIPE_TYPE_VERTICAL)
                .setDisplayViewCount(2)
                .setIsUndoEnabled(true)
                .setWidthSwipeDistFactor(15)
                .setHeightSwipeDistFactor(20)
                .setSwipeDecor(new SwipeDecor()
//                        .setMarginTop(300)
//                        .setMarginLeft(100)
//                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(0)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));
        prepareDataList();
        /*mSwipView.addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard());*/
        mSwipView.enableTouchSwipe();


    }

    private void prepareDataList() {
        int num = ProjectUtilities.imagePaths.length;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < num; i++) {
                UserPojo dataItem = new UserPojo();
                dataItem.setId("1");
                dataItem.setFirst_name(ProjectUtilities.names[i]);
                dataItem.setProfile_pic(ProjectUtilities.imagePaths[i]);
                //dataItem.setAge("23");
                dataList.add(dataItem);

                mSwipView.addView(new TinderCard(getActivity(), dataItem));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAccept:
                mSwipView.doSwipe(false);
                break;
            case R.id.btnReject:
                mSwipView.doSwipe(true);
                break;
            case R.id.btnRefresh:
                break;
            default:
                break;
        }
    }

    //This method call webservice for update latitude and longitude
    private void updateLatitudeLongitude() {


        JSONObject map = new JSONObject();

       /* try {
            map.put("lat", "" + ProjectUtilities.latitude);
            map.put("lng", "" + ProjectUtilities.longitude);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
*/


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                WebserviceUrl.GET_ALL_USER, map,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        System.out.println("Location update webservice responce: " + response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");

                return headers;
            }

        };
        jsonObjReq.setShouldCache(true);
        // stringRequest.setTag(TAG_REQUEST);
        ProjectUtilities.mVolleyQueue.add(jsonObjReq);

    }
}
