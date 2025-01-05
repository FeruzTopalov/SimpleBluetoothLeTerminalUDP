package de.kai_morich.simple_bluetooth_le_terminal;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UdpSender {

    private static boolean isUDPSendEnabled = false;
    private static InetAddress serverAddress;
    private static int serverPort;
    private static Context mycontext = null;

    //Get context from MainActivity to have an access to SharedPreferences
    public UdpSender(Context context) {
        mycontext = context.getApplicationContext();
    }

    //Get up to date prefs from UDP settings menu
    public static void retrievePrefs() {
        // Retrieve the saved value from SharedPreferences
        SharedPreferences prefs = mycontext.getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        Boolean savedUDPSwitchState = prefs.getBoolean(Constants.KEY_SWITCH_UDP_SENDER, false);
        String savedIPValue = prefs.getString(Constants.KEY_EDIT_IP, "");
        String savedPortValue = prefs.getString(Constants.KEY_EDIT_PORT, "");

        if (savedUDPSwitchState == true){
            try {
                serverAddress = InetAddress.getByName(savedIPValue);
                serverPort = Integer.parseInt(savedPortValue);

                if ((serverPort > 1023) && (serverPort < 65536)) {
                    isUDPSendEnabled = true;
                } else {
                    isUDPSendEnabled = false;
                }

            } catch (UnknownHostException e) {
                isUDPSendEnabled = false;
                throw new RuntimeException(e);
            }
        } else {
            isUDPSendEnabled = false;
        }
    }

    //Send UDP packet at IP:port prefetched after retrievePrefs() call
    public static void sendUdpPacket(byte[] message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isUDPSendEnabled == true) {
                    try {
                        DatagramSocket socket = new DatagramSocket();
                        DatagramPacket packet = new DatagramPacket(message, message.length, serverAddress, serverPort);
                        socket.send(packet);
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}
