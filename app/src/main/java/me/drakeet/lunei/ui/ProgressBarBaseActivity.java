package me.drakeet.lunei.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import me.drakeet.lunei.R;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/24 16:20
 */
public abstract class ProgressBarBaseActivity extends TitleBarActivity {

    public View mProgressView;
    private boolean isShowing;


    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mProgressView = findViewById(R.id.progress);
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2) @Override
    public void showProgress(boolean show) {
        isShowing = show;
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        closeKeyboard();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(
                    android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate()
                         .setDuration(shortAnimTime)
                         .alpha(show ? 1 : 0)
                         .setListener(new AnimatorListenerAdapter() {
                             @Override
                             public void onAnimationEnd(Animator animation) {
                                 mProgressView.setVisibility(
                                         show ? View.VISIBLE : View.GONE);
                             }
                         });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }


    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!isShowing) {
            return super.onKeyDown(keyCode, event);
        }
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                showProgress(false);
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }
}
