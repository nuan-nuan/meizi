package com.nuannuan.meizi.parser;

import com.nuannuan.meizi.bean.ImageBean;
import com.nuannuan.meizi.bean.MainBean;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author kevin.
 */
public class ParserImage {

  private static int urlgroupid(String url) {
    return Integer.parseInt(url.split("/")[3]);
  }

  public static ImageBean ParserImageContent(String html) {
    ImageBean imageBean = new ImageBean();
    Document document = Jsoup.parse(html);
    Elements links = document.select("img[src~=(?i)\\.(png|jpe?g)]");
    Element element = links.get(0).getElementsByTag("img").first();
    imageBean.setUrl(element.attr("src"));
    imageBean.setTitle(element.attr("alt"));
    return imageBean;
  }

  //获取首页的list
  public static List<MainBean> ParserMainBean(String html,String type) {
    List<MainBean> list = new ArrayList<>();
    Document doc = Jsoup.parse(html);
    Elements links = doc.select("li");//.select("a[target]");

    Element aelement;
    Element imgelement;
    for (int i = 7; i < links.size(); i++) {
      imgelement = links.get(i).select("img").first();
      aelement = links.get(i).select("a").first();
      MainBean bean = new MainBean();
      bean.setOrder(i);

      bean.setTitle(imgelement.attr("alt"));
      bean.setType(type);
      bean.setHeight(354);//element.attr("height")
      bean.setWidth(236);
      bean.setImageurl(imgelement.attr("data-original"));
      bean.setUrl(aelement.attr("href"));
      bean.setGroupid(urlgroupid(bean.getUrl()));//首页的这个是从大到小排序的 可以当做排序依据
      list.add(bean);
    }
    return list;
  }

  public static int getCount(String html) {
    Document doc = Jsoup.parse(html);
    Elements pages = doc.select("span");
    Element page = pages.get(11);

    Pattern p = Pattern.compile("[\\d*]");
    Matcher m = p.matcher(page.toString());
    StringBuilder stringBuffer = new StringBuilder();
    while (m.find()) {
      stringBuffer.append(m.group());
    }
    return Integer.parseInt(stringBuffer.toString());
  }
}
