package com.nuannuan.meizi.parser.servers;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.nuannuan.meizi.bean.ImageBean;
import com.nuannuan.meizi.parser.APIURL;
import com.nuannuan.meizi.parser.ParserImage;
import io.realm.Realm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author kevin.
 */
public class ImageServers extends IntentService {
  private String groupid;
  private int mcount;
  private String html;
  private List<ImageBean> lists = new ArrayList<>();

  private final OkHttpClient client = new OkHttpClient();

  public ImageServers(String name) {
    super(name);
  }

  @Override protected void onHandleIntent(Intent intent) {
    groupid = intent.getStringExtra("groupid");
    Intent resuleintent = new Intent(groupid);

    Realm realm = Realm.getDefaultInstance();

    List<ImageBean> latest = ImageBean.getAllImage(realm,groupid);

    if (!latest.isEmpty()) {//数据库有  直接发送广播通知

    } else {//否则加载网络 并存入数据库 通知
      try {
        html = client.newCall(APIURL.getApi(groupid)).execute().body().string();
      } catch (IOException e) {
        e.printStackTrace();
      }
      mcount = ParserImage.getCount(html);
      resuleintent.putExtra("count",mcount);
      sendBroadcast(resuleintent);

      for (int i = 1; i < mcount + 1; i++) {
        ImageBean imageBean = null;
        try {
          imageBean = fetchContent(groupid + "/" + i);
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (imageBean != null) {
          imageBean.setOrder(Integer.parseInt(groupid + i));
        }
        if (imageBean != null) {
          imageBean.setGroupid(groupid);
        }

        saveDb(realm,imageBean);

        lists.add(imageBean);
      }
    }
    sendBroadcast(resuleintent);
    realm.close();
  }

  /**
   * 抓取content
   */
  private ImageBean fetchContent(String path) throws IOException {
    String html;
    try {
      html = client.newCall(APIURL.getApi(path)).execute().body().string();
    } catch (IOException e) {
      Log.e("TAG","Failed to fetch " + path,e);
      return null;
    }

    ImageBean imageBean = ParserImage.ParserImageContent(html);//这里解析获取的HTML文本

    //其实这里不用再去解析bitmap，从HTML可以解析到的。。。至于为什么不去解析，我也不知道我当时怎么想的。。
    Response response =
        client.newCall(new Request.Builder().url(imageBean.getUrl()).build()).execute();
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(response.body().byteStream(),null,options);
    imageBean.setImagewidth(options.outWidth);
    imageBean.setImageheight(options.outHeight);
    return imageBean;
  }

  private void saveDb(Realm realm,ImageBean imageBean) {
    realm.beginTransaction();
    realm.copyToRealm(imageBean);
    realm.commitTransaction();
  }
}
