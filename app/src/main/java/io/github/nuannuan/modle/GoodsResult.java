package io.github.nuannuan.modle;

import java.util.ArrayList;

/**
 * Created by kevin.
 */
public class GoodsResult extends BaseResult {
	private ArrayList<Goods> results;

	public GoodsResult(ArrayList<Goods> results) {
		this.results = results;
	}

	public ArrayList<Goods> getResults() {
		return results;
	}

	public void setResults(ArrayList<Goods> results) {
		this.results = results;
	}
}
