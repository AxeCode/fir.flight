package io.github.ryanhoo.firFlight.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import io.github.ryanhoo.firFlight.R;
import io.github.ryanhoo.firFlight.data.model.Token;
import io.github.ryanhoo.firFlight.data.model.User;
import io.github.ryanhoo.firFlight.ui.about.AboutActivity;
import io.github.ryanhoo.firFlight.ui.base.BaseFragment;
import io.github.ryanhoo.firFlight.ui.common.alert.FlightDialog;
import io.github.ryanhoo.firFlight.ui.setting.SettingsActivity;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 7/9/16
 * Time: 12:21 AM
 * Desc: ProfileFragment
 */
public class ProfileFragment extends BaseFragment implements ProfileContract.View {


    ProfileContract.Presenter mPresenter;
    FlightDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        ProfilePresenter presenter = new ProfilePresenter(this);
        presenter.subscribe();
    }

    @Override
    public void onDestroyView() {
        mPresenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    // OnClick Events

    // Menu

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_settings:
                onOpenSettings();
                break;
            case R.id.menu_item_about:
                onOpenAbout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onOpenSettings() {
        startActivity(new Intent(getActivity(), SettingsActivity.class));
    }

    private void onOpenAbout() {
        startActivity(new Intent(getActivity(), AboutActivity.class));
    }

    // MVP View

    @Override
    public void updateUserProfile(User user) {
    }

    @Override
    public void updateApiToken(Token token) {
    }

    @Override
    public void onRefreshApiTokenStart() {
        mProgressDialog.show();
    }

    @Override
    public void onRefreshApiTokenCompleted() {
        mProgressDialog.dismiss();
    }
}
