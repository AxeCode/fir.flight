package io.github.ryanhoo.firFlight.ui.app;

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
import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.data.source.AppRepository;
import io.github.ryanhoo.firFlight.ui.base.BaseFragment;
import io.github.ryanhoo.firFlight.ui.common.DefaultItemDecoration;
import io.github.ryanhoo.firFlight.ui.helper.SwipeRefreshHelper;
import io.github.ryanhoo.firFlight.ui.play.PlayActivity;

import static io.github.ryanhoo.firFlight.ui.common.Constants.INTENT_CLASS_ID;
import static io.github.ryanhoo.firFlight.ui.common.Constants.INTENT_SUBJECT_TYPE;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 3/19/16
 * Time: 12:29 AM
 * Desc: AppListFragment
 */
public class CoursesFragment extends BaseFragment
        implements AppContract.View, SwipeRefreshLayout.OnRefreshListener, CoursesAdapter.AppItemClickListener {

    private static final String TAG = "AppListFragment";
    private static final String SUBJECT_TYPE = "subject_type";
    private static final String COURSE_ID = "course_id";

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    @Bind(R.id.empty_view)
    View emptyView;

    CoursesAdapter mAdapter;

    String subjectType;
    String courseId;
    AppContract.Presenter mPresenter;

    public static CoursesFragment newInstance(String subjectType, String courseId) {
        CoursesFragment fragment = new CoursesFragment();
        Bundle args = new Bundle();
        args.putString(SUBJECT_TYPE, subjectType);
        args.putString(COURSE_ID, courseId);
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
            subjectType = getArguments().getString(SUBJECT_TYPE);
            courseId = getArguments().getString(COURSE_ID);
        }

        SwipeRefreshHelper.setRefreshIndicatorColorScheme(swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        mAdapter = new CoursesAdapter(getActivity(), null);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DefaultItemDecoration(
                ContextCompat.getColor(getContext(), R.color.ff_white),
                ContextCompat.getColor(getContext(), R.color.ff_divider),
                getContext().getResources().getDimensionPixelSize(R.dimen.ff_padding_large)
        ));

        new AppPresenter(AppRepository.getInstance(), this, courseId).subscribe();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLoadAppStarted() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onLoadAppCompleted() {
        swipeRefreshLayout.setRefreshing(false);
        boolean isEmpty = mAdapter.getItemCount() == 0;
        emptyView.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onCourseLoaded(List<Courses> courses) {
        mAdapter.setData(courses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(AppContract.Presenter presenter) {
        mPresenter = presenter;
    }

    // SwipeRefreshListener

    @Override
    public void onRefresh() {
        mPresenter.loadApps(courseId);
    }

    // AppItemClickListener

    @Override
    public void onItemClick(int position) {
        Courses courses = mAdapter.getItem(position);
        if (courses != null) {
            Intent intent = new Intent(getActivity(), PlayActivity.class);
            intent.putExtra(INTENT_SUBJECT_TYPE, subjectType);
            intent.putExtra(INTENT_CLASS_ID, courses.getId());
            startActivity(intent);
        }
    }

    @Override
    public void onButtonClick(final CoursesItemView itemView, final int position) {
    }

}
