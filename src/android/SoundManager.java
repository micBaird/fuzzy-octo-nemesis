package com.kindara.android.soundmanager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
/**
 * This plugin provides access to the ringtone options on the device by
 * utilizing the Android RingtoneManager in order to select a ringtone sound
 * and deliver the selected sound's URI.
 */

public class SoundManager extends CordovaPlugin {
	
	private CallbackContext callbackContext;
	
	private static final int RINGTONE_PICKER_RESULT = 999;
	
	/**
	 * A constructor.
	 */
	public SoundManager() {
	}

	/**
	 * Executes the request and returns PluginResult.
	 * 
	 * @param action 			The action to execute.
	 * @param args              JSONArray of arguments for the plugin.
     * @param callbackContext   The callback context used when calling back into JavaScript.
     * @return                  True when the action was valid, false otherwise.
	 */
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
		return true;

	}

	/**
	 * Launches the RingtoneManager Ringtone Picker to select a ringtone.
	 */
	private void pickRingtone() {

		final CordovaPlugin plugin = (CordovaPlugin) this;
		Runnable worker = new Runnable() {
			public void run() {
				Intent ringtonePickerIntent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
				ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select ringtone for notifications:");
				ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
				ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
				ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,RingtoneManager.TYPE_NOTIFICATION);
				
				plugin.cordova.startActivityForResult(plugin, ringtonePickerIntent, RINGTONE_PICKER_RESULT);
			}
		};
		this.cordova.getThreadPool().execute(worker);

	}

	/**
	 * Called when user needs to pick a ringtone.
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, final Intent intent) {

		Log.d("SoundManager", "onActivityResult requestCode: " + requestCode);
		Log.d("SoundManager", "onActivityResult resultCode: " + resultCode);
		if (requestCode == RINGTONE_PICKER_RESULT) {
			if (resultCode == Activity.RESULT_OK) {
				
				Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				if (uri != null) {
					String ringtonePath = uri.toString();
					Log.d("SoundManager", "onActivityResult uri: " + ringtonePath);
					this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, ringtonePath));
					return;
				}
				else {
					this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "No ringtone path!"));
					return;
				}
				
			}
			else if (resultCode == Activity.RESULT_CANCELED) {
				this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Request code not OK"));
				return;
			}
            this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Error Unknown"));
            return;
		}

	}

}