package io.github.ryanhoo.firFlight.data.source;

import io.github.ryanhoo.firFlight.data.model.Courses;
import rx.Observable;

import java.util.List;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 6/26/16
 * Time: 12:17 AM
 * Desc: AppContract
 */
public interface AppContract {

    interface Local {

        Observable<List<Courses>> courses();

        boolean save(Courses course);

        int save(List<Courses> courses);

        boolean delete(Courses course);

        int delete(List<Courses> courses);

        int deleteAll();
    }

    interface Remote {
        Observable<List<Courses>> courses();
    }

    Observable<List<Courses>> courses();

    Observable<List<Courses>> courses(boolean forceUpdate);

}
