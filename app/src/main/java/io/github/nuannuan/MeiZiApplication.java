package io.github.nuannuan;

import android.app.Application;
import android.content.Context;


/**
 * Created by kevin.
 */
public class MeiZiApplication extends Application {
	private static Context mContext;

	public static Context getContext() {
		return mContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
	}
}
