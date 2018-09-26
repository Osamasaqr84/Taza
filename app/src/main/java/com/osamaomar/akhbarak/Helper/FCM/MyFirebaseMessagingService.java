package com.osamaomar.akhbarak.Helper.FCM;

/*
 * Created by Khalid on 12/18/2017.
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    Intent intent;

    /**
     * Called when message is received.
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
  /*  @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        JSONObject jsonObject = new JSONObject(remoteMessage.getData());


    }*/
    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param dataPayLoad FCM message body received.
     */
    private void sendNotification(JSONObject dataPayLoad) {
/*
        // saved previous  notifications in Sharedpreference
        SharedPreferences Notifpreference = PreferenceManager.getDefaultSharedPreferences(MyFirebaseMessagingService.this);
        // get data as string and convert it as string array
        string = Notifpreference.getString("notifications", "").split(";");
        // we should refer to this sharedpreference in notification Activity to display the data
*/

    }
}