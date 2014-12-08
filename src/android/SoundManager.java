package com.kindara.android.soundmanager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

public class SoundManager extends CordovaPlugin {
	
	private CallbackContext callbackContext;
	
	private static final int RINGTONE_PICKER_RESULT = 999;
	
	public SoundManager() {
	}

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		Log.d("SoundManager", "Init SoundManager");
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		this.callbackContext = callbackContext;
		Log.d("SoundManager", "SoundManager received: " + action);

		if (action.equals("pickRingtone")) {
			this.pickRingtone();
		}
		else {
			return false;

		}
		callbackContext.success();
		return true;

	}

	/**
	 * Launches the Ringtone Picker to select a single ringtone.
	 */
	private void pickRingtone() {
		final CordovaPlugin plugin = (CordovaPlugin) this;
		Runnable worker = new Runnable() {
			public void run() {
				Intent ringtonePickerIntent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
				plugin.cordova.startActivityForResult(plugin, ringtonePickerIntent, RINGTONE_PICKER_RESULT);
			}
		};
		this.cordova.getThreadPool().execute(worker);
	}

	/**
	 * Called when user picks a ringtone.
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
		if (requestCode == RINGTONE_PICKER_RESULT) {
			if (resultCode == Activity.RESULT_OK) {
				String ringtoneId = intent.getData().getLastPathSegment();

				RingtoneManager rm = new RingtoneManager(this.cordova.getActivity());
				Cursor cursor = rm.getCursor();

				Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				if (uri != null) {
					String ringtonePath = uri.toString();
				}
				else {
					callbackContext.error("No ringtone path!");
				}
				
			}
			else {
				this.callbackContext.error("Request code not OK");
			}
			super.onActivityResult(requestCode, resultCode, intent);
		}

	}

}