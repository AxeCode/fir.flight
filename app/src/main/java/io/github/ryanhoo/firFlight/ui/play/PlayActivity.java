package io.github.ryanhoo.firFlight.ui.play;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import io.github.ryanhoo.firFlight.R;
import io.github.ryanhoo.firFlight.ui.base.BaseActivity;

/**
 * Created by arthas on 01/01/2018.
 */

public class PlayActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }
}
