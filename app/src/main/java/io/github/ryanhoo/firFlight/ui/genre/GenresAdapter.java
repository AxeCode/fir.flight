package io.github.ryanhoo.firFlight.ui.genre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.github.ryanhoo.firFlight.data.model.Genre;
import io.github.ryanhoo.firFlight.ui.common.adapter.ListAdapter;
import io.github.ryanhoo.firFlight.ui.common.adapter.OnItemClickListener;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 8/21/16
 * Time: 10:48 PM
 * Desc: AppAdapterV2
 */
/* package */ class GenresAdapter extends ListAdapter<Genre, GenresItemView> {

    private AppItemClickListener mItemClickListener;

    /* package */ GenresAdapter(Context context, List<Genre> data) {
        super(context, data);
    }

    @Override
    protected GenresItemView createView(Context context) {
        return new GenresItemView(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder.itemView instanceof GenresItemView && mItemClickListener != null) {
            final GenresItemView itemView = (GenresItemView) holder.itemView;
            itemView.buttonAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onButtonClick(itemView, holder.getAdapterPosition());
                }
            });
        }
        return holder;
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder.itemView instanceof GenresItemView) {
            Genre genre = getItem(position);
            GenresItemView itemView = (GenresItemView) holder.itemView;

            itemView.buttonAction.setVisibility(View.GONE);
        }
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        super.setOnItemClickListener(listener);
        if (listener instanceof AppItemClickListener) {
            mItemClickListener = (AppItemClickListener) listener;
        }
    }

    interface AppItemClickListener extends OnItemClickListener {

        @Override
        void onItemClick(int position);

        void onButtonClick(GenresItemView itemView, int position);
    }
}
