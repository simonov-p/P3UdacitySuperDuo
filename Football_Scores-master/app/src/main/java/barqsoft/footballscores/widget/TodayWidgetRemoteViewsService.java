package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;

/**
 * Created by petr on 11-Nov-15.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TodayWidgetRemoteViewsService extends RemoteViewsService {
    private static final String[] SCORES_COLUMNS = {
            DatabaseContract.ScoresTable.HOME_COL,
            DatabaseContract.ScoresTable.AWAY_COL,
            DatabaseContract.ScoresTable.HOME_COL,
            DatabaseContract.ScoresTable.AWAY_COL
    };
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor data = null;

            @Override
            public void onCreate() {

            }

            @Override
            public void onDataSetChanged() {
                if (data == null) {
                    return;
                }
                final long identityToken = Binder.clearCallingIdentity();
                // Get score's data from the ContentProvider
                Uri gameUri = DatabaseContract.BASE_CONTENT_URI;
                data = getContentResolver().query(gameUri, SCORES_COLUMNS, null,
                        null, null);
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }

                RemoteViews views = new RemoteViews(getPackageName(), R.layout.list_item_widget_today);
                // home info

//                views.setImageViewResource(R.id.widget_icon, R.drawable.ic_launcher);
//                views.setTextViewText(R.id.score_home_text_view, homeCol + "");
//                views.setTextViewText(R.id.score_away_text_view, awayCol + "");
//                views.setTextViewText(R.id.score_home_goal_text_view, homeGoals + "");
//                views.setTextViewText(R.id.score_away_goal_text_view, awayGoals + "");
//                views.setImageViewResource(R.id.home_icon, R.drawable.manchester_city);
//                views.setImageViewResource(R.id.away_icon, R.drawable.arsenal);
//
//                // Create an Intent to launch MainActivity
//                Intent launchIntent = new Intent(this, MainActivity.class);
//                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
//                views.setOnClickPendingIntent(R.id.widget, pendingIntent);
//
//                // Tell the AppWidgetManager to perform an update on the current app widget
//                appWidgetManager.updateAppWidget(appWidgetId, views);

                return null;
            }

            @Override
            public RemoteViews getLoadingView() {
                return null;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }
        };
    }
}
