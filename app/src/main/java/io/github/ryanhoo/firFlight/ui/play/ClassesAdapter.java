package io.github.ryanhoo.firFlight.ui.play;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import io.github.ryanhoo.firFlight.data.model.Classes;
import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.ui.common.adapter.ListAdapter;
import io.github.ryanhoo.firFlight.ui.common.adapter.OnItemClickListener;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 8/21/16
 * Time: 10:48 PM
 * Desc: AppAdapterV2
 */
/* package */ class ClassesAdapter extends ListAdapter<Classes, ClassesItemView> {

    private AppItemClickListener mItemClickListener;

    /* package */ ClassesAdapter(Context context, List<Classes> data) {
        super(context, data);
    }

    @Override
    protected ClassesItemView createView(Context context) {
        return new ClassesItemView(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder.itemView instanceof ClassesItemView && mItemClickListener != null) {
            final ClassesItemView itemView = (ClassesItemView) holder.itemView;
        }
        return holder;
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder.itemView instanceof ClassesItemView) {
            Classes classes = getItem(position);
            ClassesItemView itemView = (ClassesItemView) holder.itemView;

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
    }
}
