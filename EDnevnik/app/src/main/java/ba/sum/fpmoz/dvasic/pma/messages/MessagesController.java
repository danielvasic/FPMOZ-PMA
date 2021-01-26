package ba.sum.fpmoz.dvasic.pma.messages;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import ba.sum.fpmoz.dvasic.pma.R;
import ba.sum.fpmoz.dvasic.pma.model.Messages;


public class MessagesController {

    public static void notify(@NonNull Messages message, @NonNull Context ctx) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, "ednevnik")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(message.title)
                .setContentText(message.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager =
                (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "ednevnik";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "ednevnik",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        notificationManager.notify(0, builder.build());
    }
}
