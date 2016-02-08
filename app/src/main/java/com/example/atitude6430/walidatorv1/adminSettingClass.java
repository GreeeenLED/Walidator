package com.example.atitude6430.walidatorv1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class adminSettingClass extends AppCompatActivity {

    //admin settings
    CheckBox adminSettings;
    EditText adminLogin;
    EditText adminPassword;
    //serwer settings
    CheckBox serverSettings;
    EditText host;
    EditText userName;
    EditText userPassword;
    EditText port;
    //sending settings
    CheckBox sendingSettings;
    EditText filePath;
    EditText fileName;

    public void ReadSharedPref(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.Settings),MODE_PRIVATE);
        adminLogin.setText(sharedPreferences.getString(getString(R.string.AdminLogin),""));
        adminPassword.setText(sharedPreferences.getString(getString(R.string.AdminPassword),""));

        host.setText(sharedPreferences.getString(getString(R.string.Host),""));
        userName.setText(sharedPreferences.getString(getString(R.string.UserName),""));
        userPassword.setText(sharedPreferences.getString(getString(R.string.UserPassword), ""));
        port.setText(sharedPreferences.getString(getString(R.string.Port),""));

        fileName.setText(sharedPreferences.getString(getString(R.string.FileName),""));
        filePath.setText(sharedPreferences.getString(getString(R.string.FilePath),""));
    }
    public void WriteSharedPref(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.Settings),MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();//implementation of SharedPref Editor object (for edidting purpose)
        if (adminSettings.isChecked()){
            editor.putString(getString(R.string.AdminLogin), adminLogin.getText().toString());
            editor.putString(getString(R.string.AdminPassword), adminPassword.getText().toString());
        }
        if (serverSettings.isChecked()){
            editor.putString(getString(R.string.Host), host.getText().toString());
            editor.putString(getString(R.string.UserName), userName.getText().toString());
            editor.putString(getString(R.string.UserPassword), userPassword.getText().toString());
            editor.putString(getString(R.string.Port), port.getText().toString());
        }
        if (sendingSettings.isChecked()){
            editor.putString(getString(R.string.FileName), fileName.getText().toString());
            editor.putString(getString(R.string.FilePath), filePath.getText().toString());
        }
        if (adminSettings.isChecked()||serverSettings.isChecked()||sendingSettings.isChecked()){
            editor.commit();
            Toast.makeText(getApplicationContext(),"zmiany zostały zapisane",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(),"żadne zmiany nie zostały zapisane",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_setting_class);


        //admin settings============================================================================
        adminSettings = (CheckBox) findViewById(R.id.checkBoxAdmin);
        adminLogin = (EditText) findViewById(R.id.editAdminLogin);
        adminLogin.setEnabled(false);
        adminPassword = (EditText) findViewById(R.id.editAdminHaslo);
        adminPassword.setEnabled(false);
        adminSettings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (adminSettings.isChecked()){
                    adminLogin.setEnabled(true);
                    adminPassword.setEnabled(true);
                }else
                {
                    adminLogin.setEnabled(false);
                    adminPassword.setEnabled(false);
                }
            }
        });
        //server settings===========================================================================
        serverSettings = (CheckBox) findViewById(R.id.checkBoxServer);
        host = (EditText) findViewById(R.id.editHostAdres);
        host.setEnabled(false);
        userName = (EditText) findViewById(R.id.editUserName);
        userName.setEnabled(false);
        userPassword = (EditText) findViewById(R.id.editUserPassword);
        userPassword.setEnabled(false);
        port = (EditText) findViewById(R.id.editPort);
        port.setEnabled(false);
        serverSettings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (serverSettings.isChecked()){
                    host.setEnabled(true);
                    userPassword.setEnabled(true);
                    userName.setEnabled(true);
                    port.setEnabled(true);
                }
                else {
                    host.setEnabled(false);
                    userPassword.setEnabled(false);
                    userName.setEnabled(false);
                    port.setEnabled(false);
                }
            }
        });
        //sending settings==========================================================================
        sendingSettings = (CheckBox) findViewById(R.id.checkBox);
        fileName = (EditText) findViewById(R.id.editFileName);
        fileName.setEnabled(false);
        filePath = (EditText) findViewById(R.id.editFilePath);
        filePath.setEnabled(false);
        sendingSettings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (sendingSettings.isChecked()){
                    fileName.setEnabled(true);
                    filePath.setEnabled(true);
                }else {
                    fileName.setEnabled(false);
                    filePath.setEnabled(false);
                }
            }
        });
        //read current settings=====================================================================
        ReadSharedPref();
    }
    //admin settings
    public void SaveSettings(View view){
        WriteSharedPref();
        finish();
    }
    public void Cancel(View view){
        finish();
    }

}
