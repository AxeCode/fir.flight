package io.github.ryanhoo.firFlight.ui.play;

import android.content.Context;

import java.util.List;

import io.github.ryanhoo.firFlight.data.model.Classes;
import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.ui.base.BasePresenter;
import io.github.ryanhoo.firFlight.ui.base.BaseView;
import rx.Observable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 8/21/16
 * Time: 10:17 PM
 * Desc: AppContract
 */

interface PlayContract {

    interface View extends BaseView<Presenter> {

        Context getContext();

        void onClassesLoaded(List<Classes> classes);
    }

    interface Presenter extends BasePresenter {

        void loadClasses(String subjectType, String classId);
    }

    Observable<Courses> classes(String subjectType, String classId);
}
