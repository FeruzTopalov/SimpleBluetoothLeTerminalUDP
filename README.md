# SimpleBluetoothLeTerminalUDP - Fork Description

This is a non-professional attempt to enhance the beautiful SimpleBluetoothLeTerminal project with a simple feature which allows to forward a BLE-received packet as a UDP packet to a predefined IP/Port. This feature could be used for logs collection or inter-app communication within localhost. So, changes are the next:
- Added UDP settings activity and menu
  - UDP send switch (immediate state save)
  - IP config
  - Port config
  - Save button (saves IP & Port)
- Added UDP packet forwarding to SerialSocket
  - Ensures forwarding when the app is minimized
  - Note: message entered by user in terminal is not forwarded via UDP
- Added HiLink B40 BLE module support
  - BLUETOOTH_LE_HILINK_B40_SERVICE = 0000fff0-0000-1000-8000-00805f9b34fb
  - BLUETOOTH_LE_HILINK_B40_CHAR_R = 0000fff1-0000-1000-8000-00805f9b34fb
  - BLUETOOTH_LE_HILINK_B40_CHAR_W = 0000fff2-0000-1000-8000-00805f9b34fb

**BELOW IS THE ORIGINAL README**

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3f9ba45b5c5449179150010659311f57)](https://www.codacy.com/manual/kai-morich/SimpleBluetoothLeTerminal?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=kai-morich/SimpleBluetoothLeTerminal&amp;utm_campaign=Badge_Grade)

# SimpleBluetoothLeTerminal

This Android app provides a line-oriented terminal / console for Bluetooth LE (4.x) devices implementing a custom serial profile

For an overview on Android BLE communication see 
[Android Bluetooth LE Overview](https://developer.android.com/guide/topics/connectivity/bluetooth/ble-overview).

In contrast to classic Bluetooth, there is no predefined serial profile for Bluetooth LE, 
so each vendor uses GATT services with different service and characteristic UUIDs.

This app includes UUIDs for widely used serial profiles:
- Nordic Semiconductor nRF51822  
- Texas Instruments CC254x
- Microchip RN4870/1
- Telit Bluemod

## Motivation

I got various requests asking for help with Android development or source code for my
[Serial Bluetooth Terminal](https://play.google.com/store/apps/details?id=de.kai_morich.serial_bluetooth_terminal) app.
Here you find a simplified version of my app.
