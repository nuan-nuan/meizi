package com.nuannuan.meizi.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.nuannuan.meizi.bean.MainBean;
import io.realm.Realm;
import java.util.List;

/**
 * @author kevin.
 */
public class MainActivity extends AppCompatActivity {

  private String type;
  private Realm realm;
  private IntentFilter mIntentFilter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mIntentFilter = new IntentFilter();
    registerReceiver(Receiver,mIntentFilter);
    realm = Realm.getDefaultInstance();
  }

  private BroadcastReceiver Receiver = new BroadcastReceiver() {

    @Override public void onReceive(Context context,Intent intent) {
      if (intent.getAction().equals(type)) {
        boolean isRefreshe, isLoadmore, isFirstload;
        List<MainBean> latest = MainBean.all(realm,type);
      }
    }
  };
}
