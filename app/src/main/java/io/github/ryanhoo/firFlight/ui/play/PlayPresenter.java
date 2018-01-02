package io.github.ryanhoo.firFlight.ui.play;

import java.util.List;

import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.data.source.AppRepository;
import io.github.ryanhoo.firFlight.data.source.ClassesRepository;
import io.github.ryanhoo.firFlight.data.source.PlayContract;
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
/* package */ class PlayPresenter implements PlayContract.Presenter {

    private ClassesRepository mRepository;
    private PlayContract.View mView;
    private CompositeSubscription mSubscriptions;
    private String mClassId;

    /* package */ PlayPresenter(ClassesRepository repository, PlayContract.View view, String classId) {
        mRepository = repository;
        mView = view;
        mClassId = classId;
        mView.setPresenter(this);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        loadClasses(mClassId);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
        mView = null;
    }

    @Override
    public void loadClasses(String classId) {
        Subscription subscription = mRepository.classes(classId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribe(new NetworkSubscriber<List<Courses>>(mView.getContext()) {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onNext(Courses courses) {
                        mView.onClassesLoaded(courses);
                    }

                    @Override
                    public void onUnsubscribe() {
                    }
                });
        mSubscriptions.add(subscription);
    }
}
