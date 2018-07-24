package io.github.ryanhoo.firFlight.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

//import com.crashlytics.android.answers.Answers;

import butterknife.Bind;
import butterknife.ButterKnife;
//import io.fabric.sdk.android.Fabric;
import io.github.ryanhoo.firFlight.R;
import io.github.ryanhoo.firFlight.RxBus;
import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.event.SignOutEvent;
import io.github.ryanhoo.firFlight.ui.app.CoursesFragment;
import io.github.ryanhoo.firFlight.ui.base.BaseActivity;
import io.github.ryanhoo.firFlight.ui.base.BaseFragment;
import io.github.ryanhoo.firFlight.ui.common.Constants;
import io.github.ryanhoo.firFlight.ui.message.MessagesFragment;
import io.github.ryanhoo.firFlight.ui.profile.ProfileFragment;
import io.github.ryanhoo.firFlight.ui.signin.SignInActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 7/8/16
 * Time: 9:28 PM
 * Desc: MainActivityV2
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        Fabric.with(this, new Answers());
        ActionBar actionBar = supportActionBar(toolbar);
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
        }

        final String[] tabTitles = getResources().getStringArray(R.array.ff_main_tab_titles);
        final BaseFragment[] fragments = new BaseFragment[tabTitles.length];
        //语文
        fragments[0] = CoursesFragment.newInstance(Constants.SUBJECT_CHINESE, "586d234f5f07127674152c55");
        //数学
        fragments[1] = CoursesFragment.newInstance(Constants.SUBJECT_MATH, "58f0331bc6bdf222b1744e10");
        //英语
        fragments[2] = CoursesFragment.newInstance(Constants.SUBJECT_ENGLISH, "586d234f5f07127674152c43");
        fragments[3] = CoursesFragment.newInstance(Constants.SUBJECT_HISTORY, "586d234f5f07127674152c50");
        fragments[4] = CoursesFragment.newInstance(Constants.SUBJECT_POLITICS, "586d234f5f07127674152c5f");
        //物理
//        fragments[1] = CoursesFragment.newInstance(Constants.SUBJECT_PHYSICS, "586d234f5f07127674152c4b");
        fragments[5] = new ProfileFragment();

        MainTabAdapter adapter = new MainTabAdapter(getSupportFragmentManager(), tabTitles, fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.ff_padding_large));

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    // RxBus Events

    @Override
    protected Subscription subscribeEvents() {
        return RxBus.getInstance().toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof SignOutEvent) {
                            onSignOutEvent();
                        }
                    }
                })
                .subscribe(RxBus.defaultSubscriber());
    }

    private void onSignOutEvent() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }
}
