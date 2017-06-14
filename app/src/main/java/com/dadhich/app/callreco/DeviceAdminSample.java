package com.dadhich.app.callreco;

import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by DeLL on 13/06/2017.
 */

public class DeviceAdminSample extends DeviceAdminReceiver {
    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        showToast(context,context.getString(R.string.admin_reciver_status_enable));
    }

   @Override
   public void onDisabled(Context context,Intent intent)
   {
       showToast(context,context.getString(R.string.admin_reciver_activity_disable));
   }


    void showToast(Context context, String msg) {
        String status = context.getString(R.string.admin_receiver_status , msg);
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();





    }


    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        context.stopService(new Intent(context, Tservice.class));
        Intent myIntent = new Intent(context, Tservice.class);
        context.startService(myIntent);

    }
}