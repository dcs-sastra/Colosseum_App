package colosseum19.a300dpi.colosseum2k19.Utilities;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.util.Date;
import java.util.List;

import colosseum19.a300dpi.colosseum2k19.HomeActivity;
import colosseum19.a300dpi.colosseum2k19.R;

/**
 * Created by  Raju on 12/6/2017.
 */

public class NotificationUtils {


    private static String TAG = NotificationUtils.class.getSimpleName();

    private Context mContext;

    public NotificationUtils(Context mContext) {

        this.mContext = mContext;
    }

    /**
     * Method checks if the app is in background or not
     */
    public static boolean isAppIsInBackground(final Context context, final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null)
        {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    //used to send notification
    public void sendNotification(String title, String message) {
        Intent intent = new Intent(mContext, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent,
                PendingIntent.FLAG_IMMUTABLE);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext, "participant")
                .setAutoCancel(true)   //Automatically delete the notification
                .setSmallIcon(R.drawable.logo_12) //Notification icon
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(defaultSoundUri);


        NotificationManager notificationManager = (NotificationManager)
                mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(getRandomID(), notificationBuilder.build());
    }

    private int getRandomID() {
        return (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
    }

    public void sendNotificationOreo(String title, String message, String channelID) {

        String channelId = channelID; // your notification channel id here
        String messageBody = message; // your notification message here

        NotificationCompat.Builder notificationBuilder = buildNotification(messageBody, channelId);
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        // From Android Oreo onwards notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_HIGH);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        if (notificationManager != null) notificationManager.notify(getRandomID(), notificationBuilder.build());
    }

    private NotificationCompat.Builder buildNotification(String messageBody, String channelId) {

        Intent intent = new Intent(mContext, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(mContext, channelId)
                        .setContentTitle(mContext.getString(R.string.app_name))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(messageBody))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        //In many Lollipop and above devices there is an issue displaying an icon with colored background

        notificationBuilder.setSmallIcon(R.drawable.logo_12);
        notificationBuilder.setColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        return notificationBuilder;
    }
}