package io.github.ryanhoo.firFlight.data.source;

import java.util.List;

import io.github.ryanhoo.firFlight.data.Injection;
import io.github.ryanhoo.firFlight.data.model.Genre;
import io.github.ryanhoo.firFlight.data.source.remote.RemoteGenresDataSource;
import rx.Observable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 6/26/16
 * Time: 12:47 AM
 * Desc: AppRepository
 */
public class GenreRepository implements GenreContract {

    private static GenreRepository sInstance;

    Remote mRemoteDataSource;

    private GenreRepository() {
        mRemoteDataSource = new RemoteGenresDataSource(Injection.provideRESTfulApi());
    }

    public static GenreRepository getInstance() {
        if (sInstance == null) {
            synchronized (GenreRepository.class) {
                if (sInstance == null) {
                    sInstance = new GenreRepository();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<List<Genre>> genres(String genreId) {
        final Observable<List<Genre>> remote = mRemoteDataSource.genres(genreId);
        return remote;
    }



}
