Cordova Android Sound Manager Plugin
==============================

The purpose of sound manager is to enable an Android application to allow its users to choose a sound that they want to occur in the result of an  event. For example, the plugin will allow a user to choose the sound they want to occur for a specific notification.

### How it appears to the user
Users will be prompted by the native Android [ACTION_RINGTONE_PICKER][android_RingtoneManager] to select a sound.

### Examples of Sound Usage
Sounds are ideally suited for applications with events that are customizable by the user, such as calendar and to-do list applications, or any application that triggers an alert to the user to complete an action immediately.

For example, if an application is presenting a notification to inform their user of an event, the user can select a specific notification sound to associate with the application's notifications. If an application is triggering an event to remind the user to do something immediately, the user can also be prompted to select a ringtone that will play until the notification is addressed.

A sound that is customized by the application can also be selected by the user.


### Plugin's Purpose
The purpose of the plugin is to create a platform independent javascript interface for [Cordova][cordova] based Android applications to access the specific API for Android.

## Supported Platforms
- **Android**<br>
See below for detailed informations and code segments.

- **iOS**<br>
As stated above, this plugin is specifically created for Android, therefore iOS is not supported.

- **WP8**<br>
As stated above, this plugin is specifically created for Android, therefore WP8 is not supported.


## Dependencies
[Cordova][cordova] will check all dependencies and install them if they are missing.
- [org.apache.cordova.device][apache_device_plugin] *(since v0.6.0)*

## Installation
The plugin can either be installed into the local development environment or cloud based through [PhoneGap Build][PGB]

### Adding the Plugin to your project
Through the [Command-line Interface][CLI]:
```bash
#~~ from master ~~
cordova plugin add https://github.com/micBaird/fuzzy-octo-nemesis.git && cordova prepare
```

### Removing the Plugin from your project
Through the [Command-line Interface][CLI]:
```bash
#~~ from master ~~
cordova plugin rm https://github.com/micBaird/fuzzy-octo-nemesis.git
```

### PhoneGap Build
Add the following xml to your config.xml to always use the latest version of this plugin:
```xml
<gap:plugin name="com.kindara.android.soundmanager" />
```
More informations can be found [here][PGB_plugin].

## ChangeLog
#### Version 0.3.0
- added ability to select ringtone or notification sounds separately

#### Version 0.2.0
- initial release


## Using the plugin
1. [navigator.ringtones.findRingtones][findRT]
2. [navigator.ringtones.findNotificationSounds][findNS]

### Plugin initialization
The plugin and its methods are not available before the *deviceready* event has been fired.

```javascript
document.addEventListener('deviceready', function () {
    // navigator.ringtones is now available
}, false);
```

### Prompt user to select ringtone
Users can be prompted to select a ringtone that will play continuously until notification is checked through the 'navigator.ringtones.findRingtones' interface.<br>
The method takes a function (resultCallback) as an argument to specify the event that occurs after the user has selected a ringtone as well as another function (failureCallback) that is triggered in the event that an error occurs.

```javascript
navigator.ringtones.findRingtone(
  function () { ... },   // A function that is executed in the success of a selected ringtone
  function () { ... }    // A function that is executed in the failure of a selected sound
);
```

### Prompt user to select notification sound
Users can be prompted to select a notification sound that will play once when the notification is triggered by implementing the 'navigator.ringtones.findNotificationSounds' interface.<br>
The method takes a function (resultCallback) as an argument to specify the event that occurs after the user has selected a ringtone as well as another function (failureCallback) that is triggered in the event that an error occurs.

```javascript
navigator.ringtones.findNotificationSounds(
  function () { ... },   // A function that is executed in the success of a selected ringtone
  function () { ... }    // A function that is executed in the failure of a selected sound
);
```


## Examples
### Returning the user-selected sound URI
```javascript
navigator.ringtones.findRingtones(
  function(sound_URI) {
    console.log(sound_URI);
  },
  function (message) {
    console.log(message);
  }
);
```
```javascript
navigator.ringtones.findNotificationSounds(
  function(sound_URI) {
    console.log(sound_URI);
  },
  function (message) {
    console.log(message);
  }
);
```

### Using the user-selected sound URI in combination with [local-notifications][local_notifications]
- You must include the local-nofications cordova plugin to excute this example.
```javascript
navigator.ringtones.findRingtones(
  function (sound_URI) {
    console.log(sound_URI);
    window.plugin.notification.local.add({ 
      message: 'Great app!',
      sound: sound_URI 
    });
  },
  function (message) {
    console.log(message);        
  }
);
```
```javascript
navigator.ringtones.findNotificationSounds(
  function (sound_URI) {
    console.log(sound_URI);
    window.plugin.notification.local.add({ 
      message: 'Great app!',
      sound: sound_URI 
    });
  },
  function (message) {
    console.log(message);        
  }
);
```

## Contributing

1. Fork it
2. Create your feature branch ('git checkout -b my-new-feature')
3. Commit your changes ('git commit -am 'Add new feature')
4. Push to the branch ('git push origin my-new-feature')
5. Create new Pull Request

## License

This software is released under the [Apache 2.0 License][apache2_license].

Written by [Michelle Baird][michelle_linkedIn]

© 2014-2015 [Kindara, Inc.][kindara_home] All rights reserved


[android_RingtoneManager]: http://developer.android.com/reference/android/media/RingtoneManager.html
[cordova]: https://cordova.apache.org
[CLI]: http://cordova.apache.org/docs/en/3.0.0/guide_cli_index.md.html#The%20Command-line%20Interface
[PGB]: http://docs.build.phonegap.com/en_US/3.3.0/index.html
[PGB_plugin]: https://build.phonegap.com/plugins/413
[apache_device_plugin]: https://github.com/apache/cordova-plugin-device
[local_notifications]: https://github.com/katzer/cordova-plugin-local-notifications
[findRT]: #prompt-user-to-select-ringtone
[findNS]: #prompt-user-to-select-notification-sound
[apache2_license]: http://opensource.org/licenses/Apache-2.0
[kindara_home]: https://www.kindara.com/home
[michelle_linkedIn]: www.linkedin.com/in/micBaird
