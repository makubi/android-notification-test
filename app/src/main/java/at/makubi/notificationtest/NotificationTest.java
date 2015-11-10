package at.makubi.notificationtest;

import android.content.Context;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationTest {

    static final int notificationSummaryId = 100;
    static final String GROUP_KEY = "group_key";

    public void testNotifications(final Context context) {

        final VoidAsyncTask task1 = new VoidAsyncTask() {
            @Override
            protected void run() {
                sleep(1000);

                issueNotification(context, 1);
            }
        };

        final VoidAsyncTask task2 = new VoidAsyncTask() {
            @Override
            protected void run() {
                sleep(1000);

                issueSummaryNotification(context, 2);
                issueNotification(context, 2);
            }
        };

        final VoidAsyncTask task3 = new VoidAsyncTask() {
            @Override
            protected void run() {
                sleep(1000);

                issueSummaryNotification(context, 3);
                issueNotification(context, 3);
            }
        };

        final VoidAsyncTask task4 = new VoidAsyncTask() {
            @Override
            protected void run() {
                sleep(1000);

                issueSummaryNotification(context, 4);
                issueNotification(context, 4);
            }
        };

        task1.executeSerially();
        task2.executeSerially();
        task3.executeSerially();
        task4.executeSerially();
    }

    private void issueNotification(Context context, int threadId) {
        NotificationCompat.Builder builder = mainBuilder(context);
        builder.setContentText("notification " + threadId);

        NotificationManagerCompat.from(context).notify(threadId + "", notificationSummaryId, builder.build());
    }

    private void issueSummaryNotification(Context context, int numberOfThreads) {
        NotificationCompat.Builder builder = mainBuilder(context);
        builder.setContentText("summary " + numberOfThreads);
        builder.setGroupSummary(true);

        // Setting sound and priority high (to enable heads-up notifications) results in displaying of non-summary notifications on the handheld
        builder.setSound(RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION));

        NotificationManagerCompat.from(context).notify(notificationSummaryId, builder.build());
    }

    private NotificationCompat.Builder mainBuilder(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setGroup(GROUP_KEY);
        builder.setContentTitle("title");
        builder.setSmallIcon(android.R.drawable.ic_input_get);

        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        return builder;
    }

    private void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
