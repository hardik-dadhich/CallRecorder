package com.dadhich.app.callreco;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import android.view.LayoutInflater;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Callogs extends FragmentActivity{

    private static final String TAG = "Callogs";

    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.call_logs, container, false);
        return view;
    if( ContextCompat.checkSelfPermission(Callogs.this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED)
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(Callogs.this,Manifest.permission.READ_CALL_LOG))
        {
                ActivityCompat.requestPermissions(Callogs.this,new String[]{Manifest.permission.READ_CALL_LOG},1);
        }
        else
        {
            ActivityCompat.requestPermissions(Callogs.this,new String[]{Manifest.permission.READ_CALL_LOG},1);
        }
    } else {


        TextView textView = (TextView)findViewById(R.id.textView1);
        textView .setText(getCallDeetals());

    }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
       switch (requestCode)
       {
           case 1 :{
               if(grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED)
               {
                         if(ContextCompat.checkSelfPermission(Callogs.this,Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED)
                         {
                             Toast.makeText(Callogs.this,"Permission granted",Toast.LENGTH_LONG).show();


                             TextView textView = (TextView)findViewById(R.id.textView1);
                             textView .setText(getCallDeetals());

                         }
               }
               else {
                   Toast.makeText(Callogs.this, "no permission granted" ,Toast.LENGTH_LONG ).show();
               }
               return;
           }
       }
    }
  private String getCallDeetals()
  {
      StringBuffer sb= new StringBuffer();
      Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI,null,null,null,null);
      int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
      int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
      int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
      int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

      sb.append("Call details : \n\n");

      while (managedCursor.moveToNext())
      {
             String phnumber = managedCursor.getString(number);
             String  callType = managedCursor.getString(type);
             String   callDate = managedCursor.getString(date);
          Date   callDayTime = new java.sql.Date(Long.valueOf(callDate));
          SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy  HH:mm");
          String dateString = formatter.format(callDayTime);
          String callDuration = managedCursor.getString(duration);

          String dir = null;
          int dirCode = Integer.parseInt(callType);
          switch (dirCode)
          {
              case  CallLog.Calls.OUTGOING_TYPE:
                  dir = "OUTGOING :";
                  break;
              case  CallLog.Calls.INCOMING_TYPE:
                  dir = "INCOMMING :";
                  break;
              case  CallLog.Calls.MISSED_TYPE:
                  dir = "MISSIED  :";
                  break;
          }
          sb.append("\nPHONE NO " + phnumber + "\nCALL TYPE :" + dir + "\nCALL DATE :" + dateString + "\nCALL DURATION :" + callDuration);

          sb.append("\n-----------------------------");
      }
      managedCursor.close();
      return sb.toString();
  }
}
