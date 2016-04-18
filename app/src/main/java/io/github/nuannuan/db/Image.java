package io.github.nuannuan.db;

import io.github.nuannuan.modle.Goods;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kevin.
 */
public class Image extends RealmObject {

	private int width = 0;
	private int height = 0;
	private int position = 0;

	@PrimaryKey
	private String _id;

	private String who;
	private String publishedAt;
	private String desc;
	private String type;
	private String url;
	private boolean used;

	private String createdAt;
	private String _ns;

	public static Image queryImageById(Realm realm, String objectId){
		RealmResults<Image> results =  realm.where(Image.class).equalTo("_id",objectId).findAll();
		if(results.size() > 0){
		Image image = results.get(0);
			return image;
		}
		return null;
	}

	public static Image queryImageByUrl(Realm realm, String objectId){
		RealmResults<Image> results =  realm.where(Image.class).equalTo("_id",objectId).findAll();
		if(results.size() > 0){
		Image image = results.get(0);
			return image;
		}
		return null;
	}

	public static Image queryFirstZeroImg(Realm realm){
		RealmResults<Image> results =  realm.where(Image.class).equalTo("width",0)
				.findAllSorted("position", Sort.DESCENDING);
		if(results.size() > 0){
			Image image = results.get(0);
			return image;
		}
		return null;
	}

	public static Image updateDbGoods(Image dbItem, Goods goods) {
		dbItem.setWho(goods.getWho());
		dbItem.setPublishedAt(goods.getPublishedAt());
		dbItem.setDesc(goods.getDesc());
		dbItem.setType(goods.getType());
		dbItem.setUrl(goods.getUrl());
		dbItem.setUsed(goods.isUsed());
		dbItem.set_id(goods.get_id());
		dbItem.setCreatedAt(goods.getCreatedAt());
		dbItem.set_ns(goods.get_ns());
		return dbItem;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String get_ns() {
		return _ns;
	}

	public void set_ns(String updatedAt) {
		this._ns = updatedAt;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
