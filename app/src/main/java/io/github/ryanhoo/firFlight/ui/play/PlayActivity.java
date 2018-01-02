package io.github.ryanhoo.firFlight.ui.play;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayerStandard;
import io.github.ryanhoo.firFlight.R;
import io.github.ryanhoo.firFlight.data.model.Classes;
import io.github.ryanhoo.firFlight.ui.base.BaseActivity;

import static io.github.ryanhoo.firFlight.ui.common.Constants.INTENT_CLASS_ID;

/**
 * Created by arthas on 01/01/2018.
 */

public class PlayActivity extends BaseActivity implements PlayContract.View, ClassesAdapter.AppItemClickListener{

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

        String classId = getIntent().getStringExtra(INTENT_CLASS_ID);
        new PlayPresenter(Class, this, classId).subscribe();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void setPresenter(PlayContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onClassesLoaded(List<Classes> classes) {

    }
}
