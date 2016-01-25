package io.github.nuannuan.callback;

import com.google.gson.Gson;

import io.github.nuannuan.modle.Images;
import io.github.nuannuan.network.callback.Callback;
import okhttp3.Response;

/**
 * Created by kevin.
 */
public abstract class ImageCallBack extends Callback<Images> {
    private static final String TAG = ImageCallBack.class.getSimpleName();

    @Override
    public Images parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        Images images = new Gson().fromJson(string, Images.class);
        return images;
    }
}
