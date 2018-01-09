package io.github.ryanhoo.firFlight.data.source.remote;

import java.util.List;

import io.github.ryanhoo.firFlight.data.model.Genre;
import io.github.ryanhoo.firFlight.data.source.GenreContract;
import io.github.ryanhoo.firFlight.data.source.remote.api.RESTFulApiService;
import rx.Observable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 7/3/16
 * Time: 9:52 PM
 * Desc: RemoteTokenDataSource
 */
public class RemoteGenresDataSource extends AbstractRemoteDataSource implements GenreContract.Remote {

    public RemoteGenresDataSource(RESTFulApiService api) {
        super(api);
    }

    @Override
    public Observable<List<Genre>> genres(String genreId) {
        return mApi.genres(genreId);
    }
}
