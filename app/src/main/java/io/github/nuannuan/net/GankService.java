package io.github.nuannuan.net;

import android.database.Observable;

import io.github.nuannuan.modle.DayGoodsResult;
import io.github.nuannuan.modle.GoodsResult;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kevin.
 */
public interface GankService {
	@GET("/data/Android/{limit}/{page}")
	Observable<GoodsResult> getAndroidGoods(
			@Path("limit") int limit,
			@Path("page") int page
	);

	@GET("/data/iOS/{limit}/{page}")
	Observable<GoodsResult> getIosGoods(
			@Path("limit") int limit,
			@Path("page") int page
	);

	@GET("/data/all/{limit}/{page}")
	Observable<GoodsResult> getAllGoods(
			@Path("limit") int limit,
			@Path("page") int page
	);

	@GET("/data/福利/{limit}/{page}")
	Observable<GoodsResult> getBenefitsGoods(
			@Path("limit") int limit,
			@Path("page") int page
	);

	@GET("/day/{year}/{month}/{day}")
	Observable<DayGoodsResult> getGoodsByDay(
			@Path("year") int year,
			@Path("month") int month,
			@Path("day") int day
	);
}
