package com.playdate.HomeModule;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.playdate.R;
import com.playdate.placeholderview.annotations.Click;
import com.playdate.placeholderview.annotations.Layout;
import com.playdate.placeholderview.annotations.NonReusable;
import com.playdate.placeholderview.annotations.Resolve;
import com.playdate.placeholderview.annotations.View;
import com.playdate.placeholderview.annotations.swipe.SwipeCancelState;
import com.playdate.placeholderview.annotations.swipe.SwipeIn;
import com.playdate.placeholderview.annotations.swipe.SwipeInState;
import com.playdate.placeholderview.annotations.swipe.SwipeOut;
import com.playdate.placeholderview.annotations.swipe.SwipeOutState;


/**
 * Created by janisharali on 19/08/16.
 */
@NonReusable
@Layout(R.layout.tinder_card_view)
public class TinderCard {

    private static int count;

    @View(R.id.ivProfileImage)
    private ImageView ivProfileImage;

    @View(R.id.tvUsernameAge)
    private TextView tvUsernameAge;

    private Context mContext;
    private UserPojo userPojo;


    public TinderCard() {
    }


    public TinderCard(Context mContext, UserPojo userPojo) {
        this.mContext = mContext;
        this.userPojo = userPojo;
    }

    @Resolve
    private void onResolved() {
        tvUsernameAge.setText(userPojo.getFirst_name() + " ,23");
        ImageLoader.getInstance().displayImage(userPojo.getProfile_pic(), ivProfileImage);
        //nameAgeTxt.setText(mProfile.getName() + ", " + mProfile.getAge());
        //locationNameTxt.setText(mProfile.getLocation());
    }


    @Click(R.id.ivProfileImage)
    private void onClick() {
        Log.d("DEBUG", "profileImageView");
    }


    @SwipeOut
    private void onSwipedOut() {
        Log.d("DEBUG", "onSwipedOut");
    }

    @SwipeCancelState
    private void onSwipeCancelState() {
        Log.d("DEBUG", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn() {
        Log.d("DEBUG", "onSwipedIn");

    }

    @SwipeInState
    private void onSwipeInState() {
        Log.d("DEBUG", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState() {
        Log.d("DEBUG", "onSwipeOutState");
    }
}
