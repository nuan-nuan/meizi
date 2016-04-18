package io.github.nuannuan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.nuannuan.widget.RatioImageView;

/**
 * Created by kevin.
 */
public class MeiZiAdapter extends RecyclerView.Adapter<MeiZiAdapter.ViewHolder> {
	private static final String TAG = MeiZiAdapter.class.getSimpleName();

	private Context mContext;

	public MeiZiAdapter(Context context) {
		mContext = context;

	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false));
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}

	public void update() {

	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.image)
		RatioImageView mPicture;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
