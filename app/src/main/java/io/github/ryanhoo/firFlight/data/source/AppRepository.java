package io.github.ryanhoo.firFlight.data.source;

import io.github.ryanhoo.firFlight.data.Injection;
import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.data.source.local.LocalAppDataSource;
import io.github.ryanhoo.firFlight.data.source.remote.RemoteAppDataSource;
import rx.Observable;
import rx.functions.Action1;

import java.util.List;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 6/26/16
 * Time: 12:47 AM
 * Desc: AppRepository
 */
public class AppRepository implements AppContract {

    private static AppRepository sInstance;

    AppContract.Local mLocalDataSource;
    AppContract.Remote mRemoteDataSource;

    private AppRepository() {
        mLocalDataSource = new LocalAppDataSource(Injection.provideContext());
        mRemoteDataSource = new RemoteAppDataSource(Injection.provideRESTfulApi());
    }

    public static AppRepository getInstance() {
        if (sInstance == null) {
            synchronized (AppRepository.class) {
                if (sInstance == null) {
                    sInstance = new AppRepository();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<List<Courses>> courses() {
        return courses(false);
    }

    @Override
    public Observable<List<Courses>> courses(boolean forceUpdate) {
        Observable<List<Courses>> local = mLocalDataSource.courses();
        Observable<List<Courses>> remote = mRemoteDataSource.courses()
                .doOnNext(new Action1<List<Courses>>() {
                    @Override
                    public void call(List<Courses> apps) {
                        mLocalDataSource.deleteAll();
                        mLocalDataSource.save(apps);
                    }
                });
        if (forceUpdate) {
            return remote;
        }
        return Observable.concat(local.first(), remote);
    }

}
