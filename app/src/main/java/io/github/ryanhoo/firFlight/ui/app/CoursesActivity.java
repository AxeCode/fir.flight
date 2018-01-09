package io.github.ryanhoo.firFlight.ui.app;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.ryanhoo.firFlight.R;
import io.github.ryanhoo.firFlight.ui.base.BaseActivity;

import static io.github.ryanhoo.firFlight.ui.common.Constants.INTENT_CLASS_ID;
import static io.github.ryanhoo.firFlight.ui.common.Constants.INTENT_SUBJECT_TYPE;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 3/24/16
 * Time: 12:33 PM
 * Desc: CoursesActivity
 */
public class CoursesActivity extends BaseActivity {

    private static final String TAG = "CoursesActivity";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    @SuppressLint("DefaultLocale")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        ButterKnife.bind(this);
        supportActionBar(toolbar);

        String subjectType = getIntent().getStringExtra(INTENT_SUBJECT_TYPE);
        String classId = getIntent().getStringExtra(INTENT_CLASS_ID);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        CoursesFragment coursesFragment = CoursesFragment.newInstance(subjectType, classId);
        transaction.replace(R.id.content, coursesFragment);
        transaction.commit();
    }
}
