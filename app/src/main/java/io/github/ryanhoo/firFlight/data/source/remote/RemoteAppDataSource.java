package io.github.ryanhoo.firFlight.data.source.remote;

import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.data.source.AppContract;
import io.github.ryanhoo.firFlight.data.source.remote.api.RESTFulApiService;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 6/26/16
 * Time: 12:44 AM
 * Desc: RemoteAppDataSource
 */
public class RemoteAppDataSource extends AbstractRemoteDataSource implements AppContract.Remote {

    public RemoteAppDataSource(RESTFulApiService api) {
        super(api);
    }

    @Override
    public Observable<List<Courses>> courses(String courseId) {
        return mApi.courses(courseId).flatMap(new Func1<List<Courses>, Observable<List<Courses>>>() {
            @Override
            public Observable<List<Courses>> call(List<Courses> courses) {
                if (courses != null) {
                    return Observable.just(courses);
                }
                return Observable.error(new Exception("No apps"));
            }
        });
    }
}
