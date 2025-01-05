package de.kai_morich.simple_bluetooth_le_terminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class UdpSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udp_settings);

        Button saveSettingsButton = findViewById(R.id.buttonSaveSettings);
        Switch enableUDPSenderSwitch = findViewById(R.id.udpSwitch);
        EditText userIPaddrEditText = findViewById(R.id.ipEditText);
        EditText userIPportEditText = findViewById(R.id.portEditText);

        // Retrieve the saved value from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        Boolean savedUDPSwitchState = prefs.getBoolean(Constants.KEY_SWITCH_UDP_SENDER, false);
        String savedIPValue = prefs.getString(Constants.KEY_EDIT_IP, "127.0.0.1");
        String savedPortValue = prefs.getString(Constants.KEY_EDIT_PORT, "60100");

        // Set the saved values
        enableUDPSenderSwitch.setChecked(savedUDPSwitchState);
        userIPaddrEditText.setText(savedIPValue);
        userIPportEditText.setText(savedPortValue);

        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the value in SharedPreferences
                SharedPreferences prefs = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(Constants.KEY_EDIT_IP, userIPaddrEditText.getText().toString());
                editor.putString(Constants.KEY_EDIT_PORT, userIPportEditText.getText().toString());
                editor.apply(); // or editor.commit() to save synchronously
            }
        });

        enableUDPSenderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences prefs = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(Constants.KEY_SWITCH_UDP_SENDER, enableUDPSenderSwitch.isChecked());
                editor.apply(); // or editor.commit() to save synchronously
            }
        });
    }
}





