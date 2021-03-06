package io.github.ryanhoo.firFlight.ui;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.ryanhoo.firFlight.R;
import io.github.ryanhoo.firFlight.ui.base.BaseActivity;
import io.github.ryanhoo.firFlight.ui.main.MainActivity;

import static java.lang.Math.random;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 3/15/16
 * Time: 8:27 PM
 * Desc: SplashScreenActivity
 */
public class SplashScreenActivity extends BaseActivity {

    final long ANIMATION_DURATION = 1000;
    final long SIGNED_IN_DELAY = 3500;

    String[] slogans = new String[] {
            "香香, 成功源于自信, 信心源于成绩, 成绩需要不断的努力. 加油 \n -金金",
            "生活赋予我们一种巨大的和无限高贵的礼品，这就是青春：充满着力量，充满着期待志愿，充满着求知和斗争的志向，充满着希望信心和青春。",
            "遇到困难时不要抱怨，既然改变不了过去，那么就努力改变未来。",
            "不要自卑，你不比别人笨。不要自满，别人不比你笨",
            "成功的道路上充满荆棘，苦战方能成功。",
            "You're beautiful!",
            "You gotta carry that weight, carry that weight a long time."
    };
    @Bind(R.id.text_view_app_name)
    TextView textViewAppName;
    @Bind(R.id.text_view_slogan)
    TextView textViewSlogan;

    Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        mHandler = new Handler();

        int index = (int)(Math.random() * 10) % slogans.length;
        textViewSlogan.setText(slogans[index]);
        // Animate UI in
        animateTextViews();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openMainActivity();
            }
        }, SIGNED_IN_DELAY);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // https://crazygui.wordpress.com/2010/09/05/high-quality-radial-gradient-in-android/
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        Window window = getWindow();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColors(new int[]{
                ContextCompat.getColor(this, R.color.ff_splash_gradientColor),
                ContextCompat.getColor(this, R.color.ff_splash_background)
        });
        gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gradientDrawable.setGradientRadius(screenHeight);
        gradientDrawable.setGradientCenter(screenWidth / 2, screenHeight * 0.3f);

        window.setBackgroundDrawable(gradientDrawable);
        window.setFormat(PixelFormat.RGBA_8888);
    }

    private void openMainActivity() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }


    // Animations

    private void animateTextViews() {
        final int TRANSLATE_Y = 100;
        textViewAppName.setAlpha(0f);
        textViewAppName.setTranslationY(TRANSLATE_Y);
        textViewSlogan.setAlpha(0f);
        ViewPropertyAnimatorCompat appNameAnimator = ViewCompat.animate(textViewAppName)
                .alpha(1)
                .setDuration(ANIMATION_DURATION)
                .translationYBy(-TRANSLATE_Y);
        ViewPropertyAnimatorCompat appSloganAnimator = ViewCompat.animate(textViewSlogan)
                .alpha(1)
                .setDuration(ANIMATION_DURATION);
        ViewPropertyAnimatorCompatSet animatorSet = new ViewPropertyAnimatorCompatSet();
        animatorSet.playSequentially(appNameAnimator, appSloganAnimator);
    }
}
