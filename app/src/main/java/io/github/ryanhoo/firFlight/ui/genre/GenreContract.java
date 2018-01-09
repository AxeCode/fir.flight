package io.github.ryanhoo.firFlight.ui.genre;

import android.content.Context;

import java.util.List;

import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.data.model.Genre;
import io.github.ryanhoo.firFlight.ui.base.BasePresenter;
import io.github.ryanhoo.firFlight.ui.base.BaseView;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 8/21/16
 * Time: 10:17 PM
 * Desc: AppContract
 */

interface GenreContract {

    interface View extends BaseView<Presenter> {

        Context getContext();

        void onLoadGenreStarted();

        void onLoadGenreCompleted();

        void onGenreLoaded(List<Genre> genres);
    }

    interface Presenter extends BasePresenter {

        void loadGenres(String genreId);

    }
}
