Cordova Android Sound Manager Plugin
==============================

The purpose of sound manager is to enable an Android application to allow its users to choose a sound that they want to occur in the result of an  event. For example, the plugin will allow a user to choose the sound they want to occur for a specific notification.

### How it appears to the user
Users will be prompted by the native Android sound-picker to select a sound.

### Examples of Sound Usage
Sounds are ideally suited for applications with events that are customizable by the user, such as calendar and to-do list applications, or any application that triggers an alert to the user.
For example, if applications are presenting a notification to inform their users of an event, the user can choose a specific sound to associate with that application's notifications. A sound that is made specifically for the application can also be used.

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
#### Version 0.2.0
- initial release

## Using the plugin
1. [navigator.ringtones.find][find]

### Plugin initialization
The plugin and its methods are not available before the *deviceready* event has been fired.

```javascript
document.addEventListener('deviceready', function () {
    // navigator.ringtones is now available
}, false);
```

### Prompt user to select sound
Users can be prompted to select a sound through the 'navigator.ringtones.find' interface.<br>
The method takes a function (resultCallback) as an argument to specify the event that occurs after the user has selected a ringtone as well as another function (failureCallback) that is triggered in the event that an error occurs.

```javascript
navigator.ringtones.find(
  function () { ... },   // A function that is executed in the success of a selected sound
  function () { ... }    // A function that is executed in the failure of a selected sound
);
```


## Examples
### Returning the user-selected sound URI
```javascript
navigator.ringtones.find(
  function(sound_URI) {
    console.log(sound_URI);
  },
  function (message) {
    console.log(message);
  }
);
```

### Using the user-selected sound URI in combination with [local notifications][https://github.com/katzer/cordova-plugin-local-notifications]
- You must include the local-nofications cordova plugin to excute this example.
```javascript
navigator.ringtones.find(
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

© 2014-2015 Kindara, Inc. All rights reserved


[cordova]: https://cordova.apache.org
[CLI]: http://cordova.apache.org/docs/en/3.0.0/guide_cli_index.md.html#The%20Command-line%20Interface
[PGB]: http://docs.build.phonegap.com/en_US/3.3.0/index.html
[PGB_plugin]: https://build.phonegap.com/plugins/413
[apache_device_plugin]: https://github.com/apache/cordova-plugin-device
[find]: #prompt-user-to-select-sound
