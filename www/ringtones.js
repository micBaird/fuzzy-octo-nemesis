
var exec = require('cordova/exec');

module.exports = {
	find: function(resultCallback, failureCallback) {
		exec(resultCallback, failureCallback, 'SoundManager', 'pickRingtone', []);
	}
};