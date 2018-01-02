package io.github.ryanhoo.firFlight.data.source.remote;

import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.data.source.ClassesContract;
import io.github.ryanhoo.firFlight.data.source.remote.api.RESTFulApiService;
import rx.Observable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 7/3/16
 * Time: 9:52 PM
 * Desc: RemoteTokenDataSource
 */
public class RemoteClassesDataSource extends AbstractRemoteDataSource implements ClassesContract.Remote {

    public RemoteClassesDataSource(RESTFulApiService api) {
        super(api);
    }

    @Override
    public Observable<Courses> classes(String subjectType, String classId) {
        return mApi.classes(subjectType, classId);
    }
}
