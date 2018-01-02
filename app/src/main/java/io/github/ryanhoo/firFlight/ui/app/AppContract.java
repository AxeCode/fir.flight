package io.github.ryanhoo.firFlight.ui.app;

import android.content.Context;

import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.ui.base.BasePresenter;
import io.github.ryanhoo.firFlight.ui.base.BaseView;

import java.util.List;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 8/21/16
 * Time: 10:17 PM
 * Desc: AppContract
 */

interface AppContract {

    interface View extends BaseView<Presenter> {

        Context getContext();

        void onLoadAppStarted();

        void onLoadAppCompleted();

        void onCourseLoaded(List<Courses> courses);
    }

    interface Presenter extends BasePresenter {

        void loadApps(String courseId);

    }
}
