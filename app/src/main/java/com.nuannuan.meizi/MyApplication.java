package com.nuannuan.meizi;

import android.app.Application;
import android.content.Context;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author kevin.
 */
public class MyApplication extends Application {
  private static Context mContext;
  private RefWatcher mRefWatcher;

  @Override public void onCreate() {
    super.onCreate();
    mRefWatcher = LeakCanary.install(this);
    mContext = getApplicationContext();
  }

  public static Context getContext() {
    return mContext;
  }

  public static RefWatcher getRefWatcher(Context context) {
    MyApplication application = (MyApplication) context.getApplicationContext();
    return application.mRefWatcher;
  }
}
