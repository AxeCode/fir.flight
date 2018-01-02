package io.github.ryanhoo.firFlight.ui.play;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import io.github.ryanhoo.firFlight.R;
import io.github.ryanhoo.firFlight.data.model.Classes;
import io.github.ryanhoo.firFlight.data.source.ClassesRepository;
import io.github.ryanhoo.firFlight.ui.base.BaseActivity;

import static io.github.ryanhoo.firFlight.ui.common.Constants.INTENT_CLASS_ID;
import static io.github.ryanhoo.firFlight.ui.common.Constants.INTENT_SUBJECT_TYPE;

/**
 * Created by arthas on 01/01/2018.
 */

public class PlayActivity extends BaseActivity implements PlayContract.View, ClassesAdapter.AppItemClickListener {

    @Bind(R.id.video_player)
    JZVideoPlayerStandard videoPlayer;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    ClassesAdapter mAdapter;
    PlayContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);

        mAdapter = new ClassesAdapter(this, null);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String subjectType = getIntent().getStringExtra(INTENT_SUBJECT_TYPE);
        String classId = getIntent().getStringExtra(INTENT_CLASS_ID);
        new PlayPresenter(ClassesRepository.getInstance(), this, subjectType, classId).subscribe();
    }

    @Override
    public void onItemClick(int position) {
        Classes classes = mAdapter.getItem(position);
        if (classes.isChapter()) {
            return;
        }
        List<Classes> classesList = mAdapter.getData();
        for (int i = 0; i < classesList.size(); i++) {
            Classes cls = classesList.get(i);
            if (i == position) {
                cls.setSelect(true);
            } else {
                cls.setSelect(false);
            }
        }
        mAdapter.notifyDataSetChanged();
        startPlay(classes);
    }

    private void startPlay(Classes classes) {
        if (classes != null && !TextUtils.isEmpty(classes.getHls())) {
            JZVideoPlayer.releaseAllVideos();
            videoPlayer.setUp(classes.getHls(), JZVideoPlayer.SCREEN_WINDOW_NORMAL, classes.getName());
            videoPlayer.startVideo();
            JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();

        //Change these two variables back
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    @Override
    public void setPresenter(PlayContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClassesLoaded(List<Classes> classes) {
        if (classes != null && !classes.isEmpty()) {
            for (Classes cls : classes) {
                if (cls != null && !cls.isChapter()) {
                    cls.setSelect(true);
                    startPlay(cls);
                    break;
                }
            }
        }
        mAdapter.setData(classes);
        mAdapter.notifyDataSetChanged();
    }
}
