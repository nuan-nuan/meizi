package io.github.nuannuan.db;

import android.content.Context;

import java.io.FileNotFoundException;

import io.github.nuannuan.utils.Logger;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by kevin.
 */
public class RealmHelper {
	private static final String TAG = RealmHelper.class.getSimpleName();

	public static Realm getRealm(Context context) {
		RealmConfiguration configuration = new RealmConfiguration.Builder(context)
				.name("gank.io")
				.schemaVersion(0)
				.build();
		try {
			Realm.migrateRealm(configuration, new MeiziMigration());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.d(TAG, "RealmHelp" + e);
		}
		return Realm.getInstance(configuration);
	}
}
