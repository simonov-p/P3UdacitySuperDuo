package barqsoft.footballscores.widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;

/**
 * Created by petr on 09-Nov-15.
 */
public class ScoreWidgetIntentService extends IntentService {
    private static final String[] SCORES_COLUMNS = {
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.AWAY_COL,
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.AWAY_COL
    };

    // these indices must match the projection
    private static final int INDEX_HOME_COL = 0;
    private static final int INDEX_AWAY_COL= 1;
    private static final int INDEX_HOME_GOALS = 2;
    private static final int INDEX_AWAY_GOALS = 3;

//    //Table data
//    public static final String LEAGUE_COL = "league";
//    public static final String DATE_COL = "date";
//    public static final String TIME_COL = "time";
//    public static final String HOME_COL = "home";
//    public static final String AWAY_COL = "away";
//    public static final String HOME_GOALS_COL = "home_goals";
//    public static final String AWAY_GOALS_COL = "away_goals";
//    public static final String MATCH_ID = "match_id";
//    public static final String MATCH_DAY = "match_day";

    public ScoreWidgetIntentService() {
        super("ScoreWidgetIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,
                ScoreWidgetProvider.class));

        // Get score's data from the ContentProvider
        Uri gameUri = DatabaseContract.BASE_CONTENT_URI;
        Cursor data = getContentResolver().query(gameUri, SCORES_COLUMNS, null,
                null, null);



        Log.e("mytag:", "here");

        if (data == null) {
            Log.e("mytag:data", "null");

            return;
        }
        if (!data.moveToFirst()) {
            Log.e("mytag:data", "moveToFirst");

            data.close();
            return;
        }

        // Extract the weather data from the Cursor
        String homeCol = data.getString(INDEX_HOME_COL);
        String awayCol = data.getString(INDEX_AWAY_COL);
        int homeGoals = data.getInt(INDEX_HOME_GOALS);
        int awayGoals = data.getInt(INDEX_AWAY_GOALS);
        data.close();
        Log.e("mytag:data", data.toString());
        Log.e("mytag:data", homeCol + awayCol +  homeGoals + awayGoals);


        // Perform this loop procedure for each Today widget
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_score);
            views.setImageViewResource(R.id.widget_icon, R.drawable.ic_launcher);
            views.setTextViewText(R.id.score_text_view, homeCol);

            // Create an Intent to launch MainActivity
            Intent launchIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }



    }
    private void init(){

    }
}
