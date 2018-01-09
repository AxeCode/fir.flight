package io.github.ryanhoo.firFlight.ui.genre;

import java.util.List;

import io.github.ryanhoo.firFlight.data.model.Genre;
import io.github.ryanhoo.firFlight.data.source.GenreRepository;
import io.github.ryanhoo.firFlight.network.NetworkSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 8/21/16
 * Time: 10:23 PM
 * Desc: AppPresenter
 */
/* package */ class GenrePresenter implements GenreContract.Presenter {

    private GenreRepository mRepository;
    private GenreContract.View mView;
    private CompositeSubscription mSubscriptions;
    private String mGenreId;

    /* package */ GenrePresenter(GenreRepository repository, GenreContract.View view, String genreId) {
        mRepository = repository;
        mView = view;
        mGenreId = genreId;
        mView.setPresenter(this);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        loadGenres(mGenreId);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
        mView = null;
    }

    @Override
    public void loadGenres(String genreId) {
        Subscription subscription = mRepository.genres(genreId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribe(new NetworkSubscriber<List<Genre>>(mView.getContext()) {
                    @Override
                    public void onStart() {
                        mView.onLoadGenreStarted();
                    }

                    @Override
                    public void onNext(List<Genre> genres) {
                        mView.onGenreLoaded(genres);
                    }

                    @Override
                    public void onUnsubscribe() {
                        mView.onLoadGenreCompleted();
                    }
                });
        mSubscriptions.add(subscription);
    }
}
