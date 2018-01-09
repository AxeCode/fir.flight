package io.github.ryanhoo.firFlight.data.source;

import java.util.List;

import io.github.ryanhoo.firFlight.data.model.Genre;
import rx.Observable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 6/26/16
 * Time: 12:17 AM
 * Desc: AppContract
 */
public interface GenreContract {

    interface Local {
    }

    interface Remote {
        Observable<List<Genre>> genres(String genreId);
    }

    Observable<List<Genre>> genres(String genreId);

}
