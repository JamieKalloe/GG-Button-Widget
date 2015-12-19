package net.kalloe.ggbutton;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by Jamie on 18-12-2015.
 */
public class GGWidgetProvider extends AppWidgetProvider {

    private static final String SYNC_CLICKED = "ggButtonWidgetClick";
    private MediaPlayer ggSound;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ggbutton_widget);
        ComponentName ggButtonWidget = new ComponentName(context, GGWidgetProvider.class);

        views.setOnClickPendingIntent(R.id.ibWidgetGG, getPendingIntent(context, SYNC_CLICKED));
        appWidgetManager.updateAppWidget(ggButtonWidget, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        try {
            if(ggSound == null)
                ggSound = MediaPlayer.create(context, R.raw.ggsound);

            if(SYNC_CLICKED.equals(intent.getAction()) && !ggSound.isPlaying()) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ggbutton_widget);
                ComponentName ggButtonWidget = new ComponentName(context, GGWidgetProvider.class);

                ggSound.start();

                appWidgetManager.updateAppWidget(ggButtonWidget, views);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Please, GG with moderation", Toast.LENGTH_SHORT).show();
        }
    }

    protected PendingIntent getPendingIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
