package io.github.ryanhoo.firFlight.data.source;

import io.github.ryanhoo.firFlight.data.model.Courses;
import rx.Observable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 7/2/16
 * Time: 9:23 PM
 * Desc: MessageContract
 */
public interface ClassesContract {

    interface Local {

    }

    interface Remote {
        Observable<Courses> classes(String subjectType, String classId);
    }

    Observable<Courses> classes(String subjectType, String classId);
}
