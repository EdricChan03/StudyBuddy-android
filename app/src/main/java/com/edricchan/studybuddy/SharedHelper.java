package com.edricchan.studybuddy;

import android.content.Context;

import com.edricchan.studybuddy.interfaces.Notification;
import com.edricchan.studybuddy.interfaces.NotificationAction;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class SharedHelper {
	/**
	 * Intent for notification settings action button for notifications
	 */
	public static final String ACTION_NOTIFICATIONS_SETTINGS = "com.edricchan.studybuddy.intent.ACTION_NOTIFICATIONS_SETTINGS";

	private Context mContext;

	public SharedHelper(Context context) {
		this.mContext = context;
	}

	/**
	 * Creates a notification action
	 *
	 * @return {@link NotificationAction} The notification action
	 */
	private List<NotificationAction> addDefaultNotificationActions() {
		List<NotificationAction> notificationActionList = new ArrayList<>();
		notificationActionList.add(new NotificationAction("Configure notifications", ACTION_NOTIFICATIONS_SETTINGS));
		return notificationActionList;
	}

	/**
	 * Sends a notification to the user
	 *
	 * @param user                  The username
	 * @param message               The message to send
	 * @param notificationChannelId The notification channel ID as a string
	 * @param notificationActions   The notification actions
	 */
	public void sendNotificationToUser(String user, String message, String body, String notificationChannelId, List<NotificationAction> notificationActions) {
		FirebaseFirestore db = FirebaseFirestore.getInstance();
		CollectionReference notifications = db.collection("notificationRequests");
		Notification notification = new Notification(user, message, body, notificationChannelId, notificationActions);
		notifications.add(notification);
	}

	/**
	 * Sends a notification to the user
	 *
	 * @param user                  The username
	 * @param message               The message to send
	 * @param notificationChannelId The notification channel ID as a string
	 */
	public void sendNotificationToUser(String user, String message, String notificationChannelId) {
		this.sendNotificationToUser(user, message, "", notificationChannelId, this.addDefaultNotificationActions());
	}

	/**
	 * Sends a notification to the user
	 *
	 * @param user                  The username
	 * @param message               The message to send
	 * @param body                  The body to send
	 * @param notificationChannelId The notification channel ID as a string
	 */
	public void sendNotificationToUserWithBody(String user, String message, String body, String notificationChannelId) {
		this.sendNotificationToUser(user, message, body, notificationChannelId, this.addDefaultNotificationActions());
	}

	/**
	 * Sends a notification to the user
	 *
	 * @param user                  The username
	 * @param message               The message to send
	 * @param notificationChannelId The notification channel ID as a resource
	 */
	public void sendNotificationToUser(String user, String message, int notificationChannelId) {
		this.sendNotificationToUser(user, message, "", mContext.getString(notificationChannelId), this.addDefaultNotificationActions());
	}

	/**
	 * Sends a notification to the user
	 *
	 * @param user                  The username
	 * @param message               The message to send
	 * @param body                  The body to send
	 * @param notificationChannelId The notification channel ID as a resource
	 */
	public void sendNotificationToUserWithBody(String user, String message, String body, int notificationChannelId) {
		this.sendNotificationToUser(user, message, body, mContext.getString(notificationChannelId), this.addDefaultNotificationActions());
	}

	/**
	 * Sends a notification to the user
	 * Note: The channel id will be assumed to be <code>uncategorised</code>
	 *
	 * @param user    The username
	 * @param message The message to send
	 */
	public void sendNotificationToUser(String user, String message) {
		this.sendNotificationToUser(user, message, "", mContext.getString(R.string.notification_channel_uncategorised_id), this.addDefaultNotificationActions());
	}

	/**
	 * Sends a notification to the user
	 * Note: The channel id will be assumed to be <code>uncategorised</code>
	 *
	 * @param user    The username
	 * @param message The message to send
	 * @param body    The body to send
	 */
	public void sendNotificationToUserWithBody(String user, String message, String body) {
		this.sendNotificationToUser(user, message, body, mContext.getString(R.string.notification_channel_uncategorised_id), this.addDefaultNotificationActions());
	}
}
