package com.dadhich.app.callreco;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by DeLL on 15/06/2017.
 */

public class CallLogAdapter extends CursorAdapter {

    public CallLogAdapter(Context context,Cursor cursor)
    {
        super(context,cursor,true);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.call_logs,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
  ImageView typeimg = (ImageView) view.findViewById(R.id.typeImg);
        TextView number = (TextView) view.findViewById(R.id.number);
        TextView dateandtime = (TextView) view.findViewById(R.id.dateandtime);

        String type = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));

        switch (Integer.parseInt(type))
        {
            case CallLog.Calls.INCOMING_TYPE:
                typeimg.setImageResource(R.drawable.missed_call);
                 break;
            case CallLog.Calls.OUTGOING_TYPE:
                typeimg.setImageResource(R.drawable.incomming_call);
                break;
            case CallLog.Calls.MISSED_TYPE:
                typeimg.setImageResource(R.drawable.outgoing);
                break;
            default:
                break;


        }
        //set no for text view:
        String  numberString = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));

       String nameString = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));

        if(nameString == null)
        {
            number.setText(numberString);
        }
        else
        {
            number.setText(nameString);
        }

   //se tthe date and duration
        String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
        String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
        Date callDate = new java.sql.Date(Long.valueOf(date));
        dateandtime.setText(callDate + "," + duration + "secs");


    }
}
