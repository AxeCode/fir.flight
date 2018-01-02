package io.github.ryanhoo.firFlight.data.source;

import io.github.ryanhoo.firFlight.data.Injection;
import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.data.source.remote.RemoteClassesDataSource;
import rx.Observable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 7/3/16
 * Time: 9:54 PM
 * Desc: TokenRepository
 */
public class ClassesRepository implements ClassesContract {

    private static ClassesRepository sInstance;

    Local mLocalDataSource;
    Remote mRemoteDataSource;

    private ClassesRepository() {
        mRemoteDataSource = new RemoteClassesDataSource(Injection.provideRESTfulApi());
    }

    public static ClassesRepository getInstance() {
        if (sInstance == null) {
            synchronized (AppRepository.class) {
                if (sInstance == null) {
                    sInstance = new ClassesRepository();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<Courses> classes(String subjectType, String classId) {
        return mRemoteDataSource.classes(subjectType, classId);
    }
}
