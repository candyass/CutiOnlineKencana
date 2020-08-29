package com.rs.kencana.util;

import android.util.Log;

public class MyLogger {

    private static final String LOG_MESSAGE = "kopi";

    public static void logPesan(String pesan) {
        Log.d(LOG_MESSAGE, pesan);
    }
}
