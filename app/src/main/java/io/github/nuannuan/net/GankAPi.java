package io.github.nuannuan.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import io.github.nuannuan.MeiZiApplication;
import io.github.nuannuan.utils.Constants;
import io.github.nuannuan.utils.Logger;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kevin.
 */
public class GankAPi {
	private static final String TAG = GankAPi.class.getSimpleName();

	/**
	 * 每次加载条目
	 */
	private static final int LOAD_LIMIT = 20;
	/**
	 * 加载起始页面
	 */
	private static final int LOAD_START = 1;

	private static final String SERVER = Constants.GANK_SERVER;

	public GankService mGankService;

	private static GankAPi instance;

	private Cache mCache;

	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	private static OkHttpClient.Builder httpClient;

	public static GankAPi getInstance() {
		if (null == instance) {
			synchronized (GankAPi.class) {
				if (null == instance) {
					instance = new GankAPi();
				}
			}
		}
		return instance;
	}

	public GankAPi() {
		httpClient = new OkHttpClient.Builder();
		try {
			File cacheDir = new File(MeiZiApplication.getContext().getCacheDir().getPath(), "gank.json");
			mCache = new Cache(cacheDir, 10 * 1024 * 1024);
			OkHttpClient.Builder cache = httpClient.cache(mCache);
		} catch (Exception e) {
			Logger.d(TAG, "GankApi" + e);
			e.printStackTrace();
		}
	}

	private static Retrofit.Builder builder = new Retrofit.Builder()
			.baseUrl(SERVER)
			.addConverterFactory(GsonConverterFactory.create(gson));

	public static <S> S createService(Class<S> serviceClass){
		Retrofit retrofit=builder.client(new OkHttpClient.Builder().build()).build();
		return retrofit.create(serviceClass);

	}

}

