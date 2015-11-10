package barqsoft.footballscores.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;


/**
 * Created by petr on 09-Nov-15.
 */
public class ScoreWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.e("mytag:ScoreWidgetProvider", "onUpdate");
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_score);
//        views.setImageViewResource(R.id.widget_icon, R.drawable.ic_launcher);
//        views.setTextViewText(R.id.widget_high_temperature, "petr");
//
//        // Create an Intent to launch MainActivity
//        Intent launchIntent = new Intent(context, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
//        views.setOnClickPendingIntent(R.id.widget, pendingIntent);
//
//        // Tell the AppWidgetManager to perform an update on the current app widget
//        appWidgetManager.updateAppWidget(appWidgetIds[0], views);
        context.startService(new Intent(context, ScoreWidgetIntentService.class));
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
                                          int appWidgetId, Bundle newOptions) {
        Log.e("mytag:ScoreWidgetProvider", "onAppWidgetOptionsChanged");
        context.startService(new Intent(context, ScoreWidgetIntentService.class));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e("mytag:ScoreWidgetProvider", "onReceive");
//        if (SunshineSyncAdapter.ACTION_DATA_UPDATED.equals(intent.getAction())) {
//            context.startService(new Intent(context, ScoreWidgetIntentService.class));
//        }
    }
}