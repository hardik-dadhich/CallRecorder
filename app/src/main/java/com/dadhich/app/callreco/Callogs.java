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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.CallLog.*;
import static com.dadhich.app.callreco.R.id.main_content;


public class Callogs extends Activity {
    ListView callLogListView;
    CallLogAdapter adp;


    private static final String TAG = "Callogs";

    @Nullable

    public View onCreate(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.call_logs, container, false);
        return view;
      callLogListView = (ListView) findViewById(main_content);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentationc
            // for ActivityCompat#requestPermissions for more details.
           // return TODO;
        }
        Cursor cursor = getContentResolver().query(Calls.CONTENT_URI, null, null, null, Calls.DATE + "DESC");
        cursor.moveToFirst();

        adp = new CallLogAdapter(Callogs.this,cursor);
        callLogListView.setAdapter(adp);

    }
}
