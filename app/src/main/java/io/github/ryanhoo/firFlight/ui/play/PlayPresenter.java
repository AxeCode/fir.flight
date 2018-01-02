package io.github.ryanhoo.firFlight.ui.play;

import java.util.ArrayList;
import java.util.List;

import io.github.ryanhoo.firFlight.data.model.Classes;
import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.data.source.ClassesRepository;
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
    private String mSubjectType;

    /* package */ PlayPresenter(ClassesRepository repository, PlayContract.View view, String subjectType, String classId) {
        mRepository = repository;
        mView = view;
        mClassId = classId;
        mSubjectType = subjectType;
        mView.setPresenter(this);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        loadClasses(mSubjectType, mClassId);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
        mView = null;
    }

    @Override
    public void loadClasses(String subjectType, String classId) {
        Subscription subscription = mRepository.classes(subjectType, classId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribe(new NetworkSubscriber<Courses>(mView.getContext()) {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onNext(Courses courses) {
                        mView.onClassesLoaded(parseCourses(courses));
                    }

                    @Override
                    public void onUnsubscribe() {
                    }
                });
        mSubscriptions.add(subscription);
    }

    private List<Classes> parseCourses(Courses courses) {
        List<Classes> classes = new ArrayList<>();
        if (courses == null || courses.getLectures() == null) {
            return classes;
        }

        int chapterNum = 1;
        for (Courses.LecturesBean lecturesBean : courses.getLectures()) {
            if (lecturesBean != null) {
                Classes chapter = new Classes();
                chapter.setName(lecturesBean.getName());
                chapter.setChapter(true);
                chapter.setChapterNum(chapterNum);
                chapterNum++;
                classes.add(chapter);

                if (lecturesBean.getChildren() != null) {
                    for (Courses.LecturesBean.ChildrenBean childrenBean : lecturesBean.getChildren()) {
                        if (childrenBean != null && childrenBean.getVideo() != null && childrenBean.getVideo().getHls() != null) {
                            Courses.LecturesBean.ChildrenBean.VideoBean.HlsBeanX hlsBean = childrenBean.getVideo().getHls();
                            Classes section = new Classes();
                            section.setName(childrenBean.getName());
                            section.setHls(hlsBean.getMobileMid());
                            section.setChapter(false);
                            classes.add(section);
                        } else if (childrenBean != null && childrenBean.getHls() != null) {
                            Courses.LecturesBean.ChildrenBean.HlsBeanXX hlsBeanXX = childrenBean.getHls();
                            Classes section = new Classes();
                            section.setName(childrenBean.getName());
                            section.setHls(hlsBeanXX.getMobileMid());
                            section.setChapter(false);
                            classes.add(section);
                        }
                    }
                }
            }
        }
        return classes;
    }

}
