# Notification Test
This example shows that non-summary notifications are displayed even if a summary notification exists on some configurations.

To trigger this behavior, you must create some notifications and add them to a group.
Then trigger a summary notification that has set an sound (ringtone, vibration) and a priority of high.

## Non-Summary notifications
A notification that has set a group with setGroup(String).

## Summary notification
A notification that has set a group with setGroup(String) and setGroupSummary(true).

## Expected behavior
I would expect Android to not display any notification but the summary notification.
I set sound and priority to high to display the summary notification as heads-up notification.

## Affected Android versions
I tested this behavior on Android 5.0 and 5.1 (with an emulator).

## Resources
* http://developer.android.com/training/wearables/notifications/stacks.html