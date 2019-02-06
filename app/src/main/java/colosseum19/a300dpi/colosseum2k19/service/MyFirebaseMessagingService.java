package colosseum19.a300dpi.colosseum2k19.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import colosseum19.a300dpi.colosseum2k19.HomeActivity;
import colosseum19.a300dpi.colosseum2k19.R;
import colosseum19.a300dpi.colosseum2k19.Utilities.Constants_For_FCM;
import colosseum19.a300dpi.colosseum2k19.Utilities.NotificationUtils;

/**
 * Created by  Raju on 12/7/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage == null)
            return;

        Log.e(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

            try {
                JSONObject json = new JSONObject( remoteMessage.getData());
                handleDataMessage(json);

            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    private void handleDataMessage(JSONObject json) {

        notificationUtils = new NotificationUtils(this);
        Log.e(TAG, "push json: " + json.toString());

        try {

            JSONObject data = json;

            String title = data.getString("title");
            String message = data.getString("message");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);

            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                Log.d(TAG, "App in foreground");
                // app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(Constants_For_FCM.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationUtils.sendNotificationOreo(title, message, "1");
                }else {
                    notificationUtils.sendNotification(title, message);
                }
            } else {
                Log.d(TAG, "App in background");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationUtils.sendNotificationOreo(title, message, "1");
                }else {
                    notificationUtils.sendNotification(title, message);
                }

            }

        } catch (JSONException e) {
            Log.e(TAG, "\nJson Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }
}
