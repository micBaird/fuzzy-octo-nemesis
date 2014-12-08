
var exec = require('cordova/exec');

var ringtones = {
	find: function(successCallback, errorCallback) {

		var success = function() { alert("Success"); };
        var error = function(message) { alert("Oopsie! " + message); };

        console.log("I made it HERE");
        exec(success, error, 'SoundManager', 'pickRingtone', []);
        console.log("and here?");
	}
};

module.exports = ringtones;