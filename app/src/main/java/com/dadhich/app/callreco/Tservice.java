package com.dadhich.app.callreco;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import java.io.File;
import java.io.IOException;


public class Tservice extends Service {



    private MediaRecorder recorder;
    File audiofile;



    private boolean recordstarted = false;

    private static final String ACTION_IN = "android.intent.action.PHONE_STATE";
    private static final String ACTION_OUT = "android.intent.action.NEW_OUTGOING_CALL";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("service", "destroy");

        super.onDestroy();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_OUT);
        filter.addAction(ACTION_IN);
        CallBr br_call = new CallBr();
        this.registerReceiver(br_call, filter);
        return START_NOT_STICKY;
    }

    private void startRecording() {
        File sampleDir = new File(Environment.getExternalStorageDirectory(), "/TestRecordingDasa1");
        if (!sampleDir.exists()) {
            sampleDir.mkdirs();
        }
        String file_name = "Record";
        try {
            audiofile = File.createTempFile(file_name, ".amr", sampleDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        recorder = new MediaRecorder();
//                          recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);

        recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(audiofile.getAbsolutePath());
        try {
            recorder.prepare();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        recorder.start();
        recordstarted = true;
    }

    private void stopRecording() {
        if (recordstarted) {
            recorder.stop();
            recordstarted = false;
        }
    }






   }

