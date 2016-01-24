package me.drakeet.lunei.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import me.drakeet.lunei.common.BaseActivity;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 16/1/24 16:20
 */
public abstract class TitleBarActivity extends BaseActivity {

    protected Toolbar mToolbar;


    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToolbar = (Toolbar) provideToolbar();
        reInitActionBar();
    }


    /**
     * @return your TitleBar
     */
    public abstract View provideToolbar();


    public View.OnClickListener provideRightIconOnClickListener() {
        return null;
    }


    public void titleBarOnClick() {

    }


    private void reInitActionBar() {
        if (mToolbar == null) return;
        mToolbar.setTitle(getTitle().toString());

        mToolbar.setOnClickListener(v -> titleBarOnClick());
    }
}
