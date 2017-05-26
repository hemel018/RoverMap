package com.rana.rovermap.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by rana on 5/23/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
    @Override
    public void onTokenRefresh() {
        //Get updated instance id
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

    }
}
