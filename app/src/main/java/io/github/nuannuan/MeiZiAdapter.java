package io.github.nuannuan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.nuannuan.modle.ResultsEntity;
import io.github.nuannuan.widget.RatioImageView;

/**
 * Created by kevin.
 */
public class MeiZiAdapter extends RecyclerView.Adapter<MeiZiAdapter.ViewHolder> {
    private static final String TAG = MeiZiAdapter.class.getSimpleName();
    private List<ResultsEntity> mEntities;
    private Context mContext;

    public MeiZiAdapter(Context context) {
        mContext = context;
        mEntities = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(mEntities.get(position).getUrl())
                .into(holder.mPicture);
    }

    @Override
    public int getItemCount() {
        return mEntities.size();
    }

    public void update(List<ResultsEntity> list) {
        if (list != null) {
            mEntities = list;
            notifyDataSetChanged();
        }
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
