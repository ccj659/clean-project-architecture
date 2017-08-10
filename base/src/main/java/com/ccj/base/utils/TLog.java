package com.ccj.base.utils;

import android.util.Log;

public class TLog {
	public static final String LOG_TAG = "TLog-->";
	public static boolean DEBUG = true;//是否处在debug

	public TLog() {
	}
	
	public static final void analytics(String log) {
		if (DEBUG)
			Log.d(LOG_TAG, log);
	}

	public static final void error(String log) {
		if (DEBUG)
			Log.e(LOG_TAG, "" + log);
	}

	public static final void log(String log) {
		if (DEBUG)
			Log.e(LOG_TAG, log);
	}

	public static final void log(String tag, String log) {
		if (DEBUG)
			Log.e(tag, log);
	}

	public static final void logI(String log) {
		if (DEBUG)
			Log.i(LOG_TAG, log);
	}

	public static final void warn(String log) {
		if (DEBUG)
			Log.w(LOG_TAG, log);
	}
}
