package com.dadhich.app.callreco;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DeLL on 12/06/2017.
 */

public class MissedCalls extends Fragment {
    private static final String TAG = "MissedCalls";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.missed_calls,container,false);
        return view;
    }
}
