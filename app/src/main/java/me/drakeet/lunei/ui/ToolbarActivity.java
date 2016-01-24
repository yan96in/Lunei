package me.drakeet.lunei.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import me.drakeet.lunei.R;
import me.drakeet.lunei.common.BaseActivity;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/24 16:20
 */
public abstract class ToolbarActivity extends BaseActivity {

    protected Toolbar mToolbar;
    protected boolean mIsHidden = false;


    protected abstract int provideContentViewId();


    public boolean canBack() {
        return false;
    }


    public void onToolbarClick() {}


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mToolbar == null) {
            throw new IllegalStateException("No toolbar");
        }

        mToolbar.setOnClickListener(v -> onToolbarClick());

        setSupportActionBar(mToolbar);

        if (canBack()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    protected void setAppBarAlpha(float alpha) {
        mToolbar.setAlpha(alpha);
    }


    protected void hideOrShowToolbar() {
        mToolbar.animate()
                .translationY(mIsHidden ? 0 : -mToolbar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();

        mIsHidden = !mIsHidden;
    }
}
