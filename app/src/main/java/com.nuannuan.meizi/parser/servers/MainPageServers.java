package com.nuannuan.meizi.parser.servers;

import android.app.IntentService;
import android.content.Intent;
import com.nuannuan.meizi.bean.MainBean;
import com.nuannuan.meizi.parser.APIURL;
import com.nuannuan.meizi.parser.ParserImage;
import com.nuannuan.meizi.utils.Utils;
import com.orhanobut.logger.Logger;
import io.realm.Realm;
import java.io.IOException;
import java.util.List;
import okhttp3.OkHttpClient;

/**
 * @author kevin.
 */
public class MainPageServers extends IntentService {

  private static final String TAG = MainPageServers.class.getSimpleName();
  private final OkHttpClient client = new OkHttpClient();
  private Realm realm;
  private Intent resuleintent;
  private String type;//首页的类型  性感，日本。。
  private String html;
  private String mPage;//加载更多的

  public MainPageServers() {
    super(TAG);
  }

  @Override protected void onHandleIntent(Intent intent) {
    type = intent.getStringExtra("type");
    mPage = intent.getStringExtra("page");
    //返回结果的
    resuleintent = new Intent(type);
    realm = Realm.getDefaultInstance();

    List<MainBean> latest = MainBean.all(realm,type);

    boolean hasdata = latest.size() >= Page2int(mPage) * 24;//数据库有数据
    boolean firstload = Page2int(mPage) == 1;//第一次加载||刷新
    boolean loadmore = Page2int(mPage) != 1;//加载更多

    Logger.d(TAG,"数据库有数据:" + hasdata + "  第一次加载||刷新:" + firstload + "   loadmore:" + loadmore);

    if (hasdata) {//数据库有
      if (firstload) {//刷新
        resuleintent.putExtra("isRefreshe",true);
      }
    } else {//数据库没有 就是第一次加载
      if (loadmore) {
        resuleintent.putExtra("isLoadmore",true);
      } else {
        resuleintent.putExtra("isFirstload",true);
      }
    }
    loaddata();
    sendBroadcast(resuleintent);
    realm.close();
  }

  //加载数据
  private void loaddata() {
    try {
      html = client.newCall(APIURL.getApi(Utils.setUrl(type,mPage))).execute().body().string();
      Logger.d(TAG,"http://www.mzitu.com/" + Utils.setUrl(type,mPage));
      List<MainBean> list = ParserImage.ParserMainBean(html,type);
      saveDb(realm,list);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveDb(Realm realm,List<MainBean> list) {
    realm.beginTransaction();
    realm.copyToRealmOrUpdate(list);
    realm.commitTransaction();
  }

  int Page2int(String s) {
    int i = 1;
    if (!s.equals("")) {
      i = Integer.parseInt(s);
    }
    return i;
  }
}
