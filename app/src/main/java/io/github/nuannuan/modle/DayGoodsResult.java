package io.github.nuannuan.modle;

import java.util.ArrayList;

/**
 * Created by kevin.
 */
public class DayGoodsResult extends BaseResult {
	private DayGoods results;
	private ArrayList<String> category;

	public DayGoods getResults() {
		return results;
	}

	public void setResults(DayGoods results) {
		this.results = results;
	}

	public ArrayList<String> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}

}
