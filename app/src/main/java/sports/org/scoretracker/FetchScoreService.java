package sports.org.scoretracker;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * helper methods.
 */
public class FetchScoreService extends IntentService {
	// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
	public static final String FETCH_SCORES = "sports.org.scoretracker.action.FETCH";

	/**
	 * Starts this service to perform action Foo with the given parameters. If
	 * the service is already performing a task this action will be queued.
	 *
	 * @see IntentService
	 */
	public static void startActionFetch(final Context context) {
		final Intent intent = new Intent(context, FetchScoreService.class);
		intent.setAction(FETCH_SCORES);
		context.startService(intent);
	}

	public FetchScoreService() {
		super("FetchScoreService");
	}

	@Override
	protected void onHandleIntent(final Intent intent) {
		if (intent != null) {
			final String action = intent.getAction();
			if (FETCH_SCORES.equals(action)) {
				handleFetch();
			}
		}
	}

	/**
	 * Handle action Fetch in the provided background thread with the provided
	 * parameters.
	 */
	private void handleFetch() {
		final HttpContext localContext = new BasicHttpContext();
		final HttpClient httpClient = new DefaultHttpClient();
		final HttpGet apiGet = new HttpGet("http://api.espn.com/v1/now?apikey=8n3xhzdan3k7qkexhbgrmzfw");
		try {
			final HttpResponse response = httpClient.execute(apiGet, localContext);
		} catch (final Throwable e) {

		}

		final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
		final NotificationCompat.Builder notificationBuilder =
				new NotificationCompat.Builder(this)
						.setContentTitle("FIFA Worldcup")
						.setContentText("USA (1) - Brazil (0)");
		notificationManager.notify(100, notificationBuilder.build());
	}

}
