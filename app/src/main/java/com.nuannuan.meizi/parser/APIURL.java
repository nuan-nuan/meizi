package com.nuannuan.meizi.parser;

import okhttp3.Request;

/**
 * @author kevin.
 */
public class APIURL {
  public static final String URL = "http://www.mzitu.com/";

  public static Request getApi(String path) {
    return new Request.Builder().url(URL + path).build();
  }
}
