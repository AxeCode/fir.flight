package io.github.ryanhoo.firFlight.ui.genre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.ryanhoo.firFlight.R;
import io.github.ryanhoo.firFlight.data.model.Genre;
import io.github.ryanhoo.firFlight.data.source.GenreRepository;
import io.github.ryanhoo.firFlight.ui.app.CoursesActivity;
import io.github.ryanhoo.firFlight.ui.base.BaseFragment;
import io.github.ryanhoo.firFlight.ui.common.DefaultItemDecoration;
import io.github.ryanhoo.firFlight.ui.helper.SwipeRefreshHelper;

import static io.github.ryanhoo.firFlight.ui.common.Constants.INTENT_CLASS_ID;
import static io.github.ryanhoo.firFlight.ui.common.Constants.INTENT_SUBJECT_TYPE;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 3/19/16
 * Time: 12:29 AM
 * Desc: AppListFragment
 */
public class GenresFragment extends BaseFragment
        implements GenreContract.View, SwipeRefreshLayout.OnRefreshListener, GenresAdapter.AppItemClickListener {

    private static final String TAG = "AppListFragment";
    private static final String GENRE_ID = "genre_id";

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    @Bind(R.id.empty_view)
    View emptyView;

    GenresAdapter mAdapter;

    String genreId;
    GenreContract.Presenter mPresenter;

    public static GenresFragment newInstance(String courseId) {
        GenresFragment fragment = new GenresFragment();
        Bundle args = new Bundle();
        args.putString(GENRE_ID, courseId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_app_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            genreId = getArguments().getString(GENRE_ID);
        }

        SwipeRefreshHelper.setRefreshIndicatorColorScheme(swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        mAdapter = new GenresAdapter(getActivity(), null);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DefaultItemDecoration(
                ContextCompat.getColor(getContext(), R.color.ff_white),
                ContextCompat.getColor(getContext(), R.color.ff_divider),
                getContext().getResources().getDimensionPixelSize(R.dimen.ff_padding_large)
        ));

        new GenrePresenter(GenreRepository.getInstance(), this, genreId).subscribe();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLoadGenreStarted() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onLoadGenreCompleted() {
        swipeRefreshLayout.setRefreshing(false);
        boolean isEmpty = mAdapter.getItemCount() == 0;
        emptyView.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onGenreLoaded(List<Genre> genres) {
        mAdapter.setData(genres);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(GenreContract.Presenter presenter) {
        mPresenter = presenter;
    }

    // SwipeRefreshListener

    @Override
    public void onRefresh() {
        mPresenter.loadGenres(genreId);
    }

    // AppItemClickListener

    @Override
    public void onItemClick(int position) {
        Genre genre = mAdapter.getItem(position);
        if (genre != null) {
            Intent intent = new Intent(getActivity(), CoursesActivity.class);
            intent.putExtra(INTENT_SUBJECT_TYPE, genre.getName());
            intent.putExtra(INTENT_CLASS_ID, genre.getId());
            startActivity(intent);
        }
    }

    @Override
    public void onButtonClick(final GenresItemView itemView, final int position) {
    }

}
