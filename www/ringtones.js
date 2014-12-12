
var exec = require('cordova/exec');

module.exports = {
	findRingtones: function(resultCallback, failureCallback) {
		exec(resultCallback, failureCallback, 'SoundManager', 'pickRingtone', []);
	},

	findNotificationSounds: function(resultCallback, failureCallback) {
		exec(resultCallback, failureCallback, 'SoundManager', 'pickNotificationSound', []);
	}
};