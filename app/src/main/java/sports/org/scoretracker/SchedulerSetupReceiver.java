package sports.org.scoretracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SchedulerSetupReceiver extends BroadcastReceiver {
	private static final String APP_TAG = "test.org.sportstracker";
	private static final int EXEC_INTERVAL = 5 * 1000;

	public SchedulerSetupReceiver() {
	}

	@Override
	public void onReceive(final Context context, final Intent intent) {
		Log.d(APP_TAG, "SchedulerSetupReceiver.onReceive() called");
		final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		final Intent i = new Intent(context, FetchScoreService.class);
		i.setAction(FetchScoreService.FETCH_SCORES);
		final PendingIntent pintent = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), EXEC_INTERVAL, pintent);
	}
}
